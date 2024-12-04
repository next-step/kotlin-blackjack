package blackjack.domain

class Deck {
    val cards = mutableListOf<Card>()

    init {
        createDeck()
        shuffleDeck()
    }

    private fun createDeck() {
        for (shape in CardShape.values()) {
            for (number in CardNumber.values()) {
                cards.add(Card.create(number, shape))
            }
        }
    }

    private fun shuffleDeck() {
        cards.shuffle()
    }

    fun drawCards(count: Int): List<Card> {
        if (count <= 0) throw IllegalArgumentException(DRAW_CARD_EXCEPTION_MESSAGE)

        return List(count) { cards.removeAt(0) }
    }

    fun remainingCards(): Int = cards.size

    companion object {
        private const val DRAW_CARD_EXCEPTION_MESSAGE = "1장 이상의 카드를 뽑아야 합니다."
    }
}
