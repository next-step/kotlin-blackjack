package blackjack.domain

class Player(
    val name: String,
    private val betMoney: BetMoney,
    private val drawCard: () -> Card,
) : Participant(drawCard = drawCard) {
    val originalBetAmount get() = betMoney.getOriginalBetAmount()

    fun play(
        isDrawCard: (String) -> Boolean,
        onDrawCard: () -> Unit,
        onExitPlay: () -> Unit,
    ) {
        var shouldContinue = shouldContinueDrawing(isDrawCard)
        while (shouldContinue) {
            val isCardAdded = addCardIfAvailable(requireCard = drawCard, onDrawCard = onDrawCard)
            checkOverCardMaxSum()
            shouldContinue = isCardAdded && shouldContinueDrawing(isDrawCard)
            println("수익 ${profitMoney.getCurrentProfit()}")
        }
        onExitPlay()
    }

    fun onWin() {
        profitMoney.set(betMoney.getOriginalBetAmount())
    }

    fun onBlackJackInitially() {
        profitMoney.set(betMoney.getAmountOnBlackJack())
    }

    fun onLose() {
        profitMoney.set(betMoney.getAmountOnLose())
    }

    fun onPush() {
        profitMoney.set(betMoney.getOriginalBetAmount())
    }

    private fun checkOverCardMaxSum() {
        if (cards.isBust()) {
            val profit = betMoney.getAmountOnBust()
            profitMoney.set(profit)
        }
    }

    private fun shouldContinueDrawing(isDrawCard: (String) -> Boolean): Boolean {
        return isDrawCard(name)
    }

    override fun isAddCardEnabled(): Boolean {
        return cardsSum < Card.MAX_SUM
    }
}
