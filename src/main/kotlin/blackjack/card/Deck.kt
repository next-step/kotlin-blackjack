package blackjack.card

class Deck private constructor(private var _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()

    companion object {
        fun init(): Deck {
            val cards = Suit.values().flatMap { suit ->
                CardSymbol.values().map { symbol -> Card(suit, symbol) }
            }
            return Deck(cards.shuffled().toMutableList())
        }
    }
}
