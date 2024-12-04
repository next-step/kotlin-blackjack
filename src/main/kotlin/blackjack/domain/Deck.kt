package blackjack.domain

class Deck {
    val cards = mutableListOf<Card>()

    init {
        createDeck()
        shuffleDeck()
    }

    private fun createDeck() {
        for (shape in CardShape.entries) {
            for (number in CardNumber.entries) {
                cards.add(Card.create(number, shape))
            }
        }
    }

    private fun shuffleDeck() {
        cards.shuffle()
    }

    fun drawCards(count: Int): List<Card> {
        if (count <= 0) throw IllegalArgumentException(DRAW_CARD_EXCEPTION_MESSAGE)
        if (count > cards.size) throw IllegalArgumentException(NOT_ENOUGH_CARD_LEFT_EXCEPTION_MESSAGE)

        return List(count) { cards.removeAt(0) }
    }

    fun remainingCards(): Int = cards.size

    companion object {
        private const val DRAW_CARD_EXCEPTION_MESSAGE = "1장 이상의 카드를 뽑아야 합니다."
        private const val NOT_ENOUGH_CARD_LEFT_EXCEPTION_MESSAGE = "남은 카드가 부족합니다."
    }
}
