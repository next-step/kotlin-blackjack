package blackjack.domain

class Deck {
    val cards = mutableListOf<Card>()

    init {
        CardShape.entries.forEach { shape ->
            CardNumber.entries.forEach { number ->
                cards.add(Card.of(number, shape))
            }
        }
        cards.shuffle()
    }

    fun drawCards(count: Int): List<Card> {
        require(count > 0) { MINIMUM_CARD_COUNT_EXCEPTION_MESSAGE }
        require(cards.size >= count) { NOT_ENOUGH_CARDS_EXCEPTION_MESSAGE }
        return List(count) { cards.removeAt(0) }
    }

    companion object {
        private const val MINIMUM_CARD_COUNT_EXCEPTION_MESSAGE = "1장 이상의 카드를 뽑아야 합니다."
        private const val NOT_ENOUGH_CARDS_EXCEPTION_MESSAGE = "남은 카드가 부족합니다."
    }
}
