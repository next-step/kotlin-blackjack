package blackjack.domain

class Deck(private val numberOfDecks: Int = 1) {
    val cards = mutableListOf<Card>()

    init {
        refillDeck()
        shuffleDeck()
    }

    private fun refillDeck() {
        repeat(numberOfDecks) {
            CardShape.entries.forEach { shape ->
                CardNumber.entries.forEach { number ->
                    cards.add(Card.of(number, shape))
                }
            }
        }
    }

    private fun shuffleDeck() {
        cards.shuffle()
    }

    fun drawCards(count: Int): List<Card> {
        require(count > 0) { MINIMUM_CARD_COUNT_EXCEPTION_MESSAGE }
        if (cards.size < count) {
            refillDeck()
            shuffleDeck()
        }
        return List(count) { cards.removeAt(0) }
    }

    companion object {
        private const val MINIMUM_CARD_COUNT_EXCEPTION_MESSAGE = "1장 이상의 카드를 뽑아야 합니다."
    }
}
