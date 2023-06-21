package blackjack

class Deck(
    val cards: MutableList<Card>
) {
    constructor(vararg cards: Card) : this(cards.toMutableList())

    fun draw(): Card {
        return cards.removeLast()
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
