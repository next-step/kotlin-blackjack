package blackjack.domain

class Deck(
    private val numberOfDecks: Int = 1,
    private val shuffleStrategy: (MutableList<Card>) -> Unit = { it.shuffle() },
) {
    private val cards = mutableListOf<Card>()

    init {
        initializeDeck()
    }

    private fun initializeDeck() {
        repeat(numberOfDecks) {
            CardShape.entries.forEach { shape ->
                CardNumber.entries.forEach { number ->
                    cards.add(Card.of(number, shape))
                }
            }
        }
        shuffleStrategy(cards)
    }

    fun drawCards(count: Int): List<Card> {
        require(count > 0) { MINIMUM_CARD_COUNT_EXCEPTION_MESSAGE }
        if (cards.size < count) {
            throw IllegalStateException(NOT_ENOUGH_CARDS_EXCEPTION_MESSAGE)
        }
        return List(count) { cards.removeAt(0) }
    }

    fun peekCards(count: Int): List<Card> {
        require(count > 0) { MINIMUM_CARD_COUNT_EXCEPTION_MESSAGE }
        return cards.take(count)
    }

    fun getCardsSnapshot(): List<Card> = cards.toList()

    companion object {
        private const val MINIMUM_CARD_COUNT_EXCEPTION_MESSAGE = "1장 이상의 카드를 뽑아야 합니다."
        private const val NOT_ENOUGH_CARDS_EXCEPTION_MESSAGE = "더 이상 뽑을 카드가 없습니다."
    }
}
