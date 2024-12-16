package blackjack.domain

class Player(
    val name: String,
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

    private fun shouldContinueDrawing(isDrawCard: (String) -> Boolean): Boolean {
        return isDrawCard(name)
    }

    override fun isAddCardEnabled(): Boolean {
        return cardsSum < Card.MAX_SUM
    }
}
