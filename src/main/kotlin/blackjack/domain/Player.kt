package blackjack.domain

class Player(
    val name: String,
    private val drawCard: () -> Card
) : Participant(drawCard = drawCard) {
    private lateinit var currentCard: Card

    fun play(
        isDrawCard: (String) -> Boolean,
        onDrawCard: () -> Unit,
        onExitPlay: () -> Unit,
    ) {
        while (isDrawCard(name)) {
            currentCard = drawCard()
            val isCardAdded = addCardIfAvailable(requireCard = { currentCard }, onDrawCard = onDrawCard)
            if (isCardAdded.not()) break
        }
        onExitPlay()
    }

    override fun isAddCardEnabled(): Boolean {
        return currentCard.isOverMaxSum(cardsSum).not()
    }
}
