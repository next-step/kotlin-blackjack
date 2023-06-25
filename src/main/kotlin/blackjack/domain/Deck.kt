package blackjack.domain

class Deck(cardList: List<Card>) : Iterable<Card> {

    private val cardQueue: MutableList<Card> = MutableList(cardList.size) {
        return@MutableList cardList[it].copy()
    }

    val size: Int
        get() = cardQueue.size

    init {
        require(cardList.isNotEmpty()) { EMPTY_DECK_ERROR_MESSAGE }
    }

    fun drawCard(): Card = checkNotNull(cardQueue.removeFirstOrNull()) { EMPTY_DECK_ERROR_MESSAGE }

    fun addCard(card: Card) {
        cardQueue.add(card)
    }

    fun calculatePointOrNull(): Int? {
        return PointCalculator.calculatePoint(this)
    }

    operator fun get(index: Int): Card {
        return cardQueue[index]
    }

    override fun iterator(): Iterator<Card> {
        return cardQueue.iterator()
    }

    companion object {
        private const val EMPTY_DECK_ERROR_MESSAGE = "덱이 비어있습니다"

        private val DEFAULT_DECK: Deck by lazy {
            val list = Suit.values()
                .flatMap { addSuitCards(it) }
            Deck(list)
        }

        private fun addSuitCards(suit: Suit): List<Card> {
            return CardNumber.NUMBER_RANGE.map { Card(suit, CardNumber.of(it)) }
        }

        fun getShuffledDeck() = Deck(DEFAULT_DECK.cardQueue.shuffled())
    }
}
