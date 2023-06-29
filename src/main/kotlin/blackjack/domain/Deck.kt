package blackjack.domain

class Deck(private val cards: Cards) {

    val size: Int
        get() = cards.size

    init {
        require(cards.isNotEmpty()) { EMPTY_DECK_ERROR_MESSAGE }
    }

    fun drawCard(): Card = cards.drawCard()

    companion object {
        private const val EMPTY_DECK_ERROR_MESSAGE = "덱이 비어있습니다"

        private val DEFAULT_DECK: Deck by lazy {
            val list = Suit.values()
                .flatMap { addSuitCards(it) }
            Deck(Cards(list))
        }

        private fun addSuitCards(suit: Suit): List<Card> {
            return CardNumber.NUMBER_RANGE.map { Card(suit, CardNumber.of(it)) }
        }

        fun getShuffledDeck() = Deck(Cards(DEFAULT_DECK.cards.shuffled()))
    }
}
