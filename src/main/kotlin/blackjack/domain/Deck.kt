package blackjack.domain

class Deck(cardList: List<Card>) : Iterable<Card> {
    private val cardQueue: ArrayList<Card> = ArrayList(cardList.map { it.copy() })

    val size: Int
        get() = cardQueue.size

    init {
        require(cardList.isNotEmpty()) { EMPTY_DECK_ERROR_MESSAGE }
    }

    fun drawCard(): Card = checkNotNull(cardQueue.removeFirstOrNull()) { EMPTY_DECK_ERROR_MESSAGE }

    fun addCard(card: Card) {
        cardQueue.add(card)
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
            val list = ArrayList<Card>()
            for (suit in Suit.values()) {
                list.addSuitCards(suit)
            }
            Deck(list)
        }

        private fun MutableList<Card>.addSuitCards(suit: Suit) {
            for (number in CardNumber.NUMBER_RANGE) {
                add(Card(suit, CardNumber.of(number)))
            }
        }

        fun getShuffledDeck() = Deck(ArrayList(DEFAULT_DECK.cardQueue.shuffled()))
    }
}
