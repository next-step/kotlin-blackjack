package blackjack.domain

class CardDeck(deck: List<Card>) {
    private val deck = deck.toMutableList()

    init {
        require(deck.toSet().size == deck.size) { "중복된 카드는 포함될 수 없습니다." }
    }

    fun count(): Int = deck.size
    fun draw(): Card = deck.removeAt(0)

    companion object {
        private val NUMBER_CARD_RANGE = (2..10)
        private val ALPHABET_CARDS = listOf('A', 'J', 'K', 'Q')

        fun new(): CardDeck {
            val allCards = Symbol.values().map { symbol ->
                NUMBER_CARD_RANGE.map { NumberCard(symbol, it) } + ALPHABET_CARDS.map { AlphabetCard(symbol, it) }
            }.flatten()

            return CardDeck(allCards)
        }
    }
}
