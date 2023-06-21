package blackjack

class Deck(
    val cards: MutableList<Card>
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

    companion object {
        fun init(): Deck {
            val cards = Denomination.values()
                .flatMap { number ->
                    Suit.values()
                        .map { suit -> Card(number, suit) }
                }
                .shuffled()
                .toMutableList()
            return Deck(cards)
        }
    }
}
