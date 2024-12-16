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
        while (isDrawCard(name)) {
            val isCardAdded = addCardIfAvailable(requireCard = drawCard, onDrawCard = onDrawCard)
            if (isCardAdded.not()) break
        }
        onExitPlay()
    }

    override fun isAddCardEnabled(): Boolean {
        return cardsSum <= 21
    }
}
