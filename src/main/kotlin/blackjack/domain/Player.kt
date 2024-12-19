package blackjack.domain

class Player(
    val name: String,
    private val betMoney: BetMoney,
    private val drawCard: () -> Card,
) : Participant(drawCard = drawCard) {
    private val profitMoney: ProfitMoney = ProfitMoney()

    fun play(
        isDrawCard: (String) -> Boolean,
        onDrawCard: () -> Unit,
        onExitPlay: () -> Unit,
    ) {
        var shouldContinue = shouldContinueDrawing(isDrawCard)
        while (shouldContinue) {
            val isCardAdded = addCardIfAvailable(requireCard = drawCard, onDrawCard = onDrawCard)
            shouldContinue = isCardAdded && shouldContinueDrawing(isDrawCard)
        }
        onExitPlay()
    }

    fun getProfitMoney(gameResult: GameResult): ProfitMoney {
        val betMoneyAmount = betMoney.getAmount(gameResult)
        profitMoney.set(betMoneyAmount)
        return profitMoney
    }

    private fun shouldContinueDrawing(isDrawCard: (String) -> Boolean): Boolean {
        return isDrawCard(name)
    }

    override fun isAddCardEnabled(): Boolean {
        return cardsSum < Card.MAX_SUM
    }
}
