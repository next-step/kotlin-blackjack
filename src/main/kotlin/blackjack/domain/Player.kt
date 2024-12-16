package blackjack.domain

class Player(
    val name: String,
    private val betMoney: BetMoney,
    private val drawCard: () -> Card,
) : Participant(drawCard = drawCard) {
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

    fun getGameResultWith(dealer: Dealer): GameResult {
        return when {
            this.isBlackJackInitially && dealer.isBlackJackInitially.not() -> GameResult.BLACK_JACK
            this.isBlackJackInitially && dealer.isBlackJackInitially -> GameResult.PUSH
            else -> GameResult.fromScores(dealer.cardsSum, this.cardsSum)
        }
    }

    fun setProfitMoneyFromGameResult(result: GameResult) {
        val betMoneyAmount = result.getBetMoneyAmount(betMoney)
        profitMoney.set(betMoneyAmount)
    }

    private fun shouldContinueDrawing(isDrawCard: (String) -> Boolean): Boolean {
        return isDrawCard(name)
    }

    override fun isAddCardEnabled(): Boolean {
        return cardsSum < Card.MAX_SUM
    }
}
