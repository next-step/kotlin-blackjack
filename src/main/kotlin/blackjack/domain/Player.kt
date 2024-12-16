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
            shouldContinue = isCardAdded && shouldContinueDrawing(isDrawCard)
        }
        onExitPlay()
    }

    fun onBlackJackInitially() {
        profitMoney.set(betMoney.getAmountOnBlackJack())
    }

    fun onWin() {
        profitMoney.set(betMoney.getOriginalBetAmount())
    }

    fun onBust() {
        profitMoney.set(betMoney.getAmountOnBust())
    }

    fun onLose() {
        profitMoney.set(betMoney.getAmountOnLose())
    }

    fun onPush() {
        profitMoney.set(betMoney.getOriginalBetAmount())
    }

    private fun shouldContinueDrawing(isDrawCard: (String) -> Boolean): Boolean {
        return isDrawCard(name)
    }

    override fun isAddCardEnabled(): Boolean {
        return cardsSum < Card.MAX_SUM
    }
}
