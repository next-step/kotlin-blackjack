package blackjack.domain

@Suppress("UNREACHABLE_CODE")
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
            val isCardAdded = addCard(card, onDrawCard)
            if (isCardAdded.not()) break
        }
        onExitPlay()
    }

    private fun addCard(card: Card, onDrawCard: () -> Unit): Boolean {
        return if (canAdd(card)) {
            addCard(card)
            onDrawCard()
            true
        } else {
            false
        }
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
