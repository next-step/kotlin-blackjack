package blackjack.card

class Deck private constructor(private var _cards: Set<Card>) {
    val cards: Set<Card> get() = _cards

    fun shuffle() {
        _cards = _cards.shuffled().toSet()
    }

    companion object {
        fun init(): Deck {
            val cards = mutableSetOf<Card>()
            for (symbol in CardSymbol.values()) {
                addSymbols(cards, symbol)
            }
            return Deck(cards)
        }

        private fun addSymbols(cards: MutableSet<Card>, symbol: CardSymbol) {
            for (suit in Suit.values()) {
                cards.add(Card(suit = suit, symbol = symbol))
            }
        }
    }
}
