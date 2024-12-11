package blackjack.domain

class Player(val name: String, private val deck: Deck) {
    val cards = mutableListOf<Card>()

    init {
        repeat(DEFAULT_CARD_COUNT) { addCard(deck.draw()) }
    }

    fun play(
        isDrawCard: (String) -> Boolean,
        onDrawCard: () -> Unit,
        onExitPlay: () -> Unit,
    ) {
        while (isDrawCard(name)) {
            val card = deck.draw()
            if (canAdd(card)) {
                addCard(card)
                onDrawCard()
            } else {
                break
            }
        }
        onExitPlay()
    }

    fun getCardSum(): Int {
        return cards.sumValues()
    }

    private fun canAdd(card: Card): Boolean {
        val cardSum = getCardSum()
        return card.isOverMaxSum(cardSum).not()
    }

    private fun addCard(card: Card) {
        cards.add(card)
    }

    companion object {
        private const val DEFAULT_CARD_COUNT = 2
    }
}
