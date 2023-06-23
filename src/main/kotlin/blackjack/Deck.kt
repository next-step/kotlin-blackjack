package blackjack

class Deck(
    private val cards: MutableList<Card>
) {
    constructor(vararg cards: Card) : this(cards.toMutableList())

    fun draw(): Card {
        return cards.removeLast()
    }

    fun draw(count: Int): Cards {
        return Cards(List(count) { draw() })
    }

    fun isNotEmpty(): Boolean {
        return cards.isNotEmpty()
    }

    operator fun contains(card: Card): Boolean {
        return card in cards
    }

    fun size(): Int {
        return cards.size
    }

    companion object {
        fun shuffled(): Deck {
            val cards = Denomination.values()
                .flatMap { number ->
                    Suit.values()
                        .map { suit -> Card.of(number, suit) }
                }
                .shuffled()
                .toMutableList()
            return Deck(cards)
        }
    }
}
