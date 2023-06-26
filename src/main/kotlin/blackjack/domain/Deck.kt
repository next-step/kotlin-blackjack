package blackjack.domain

class Deck(
    private val cards: MutableList<Card>
) {
    val size: Int
        get() = cards.size

    fun draw(): Card = cards.removeLast()

    fun draw(count: Int): Cards {
        return Cards(List(count) { draw() })
    }

    fun isNotEmpty(): Boolean {
        return cards.isNotEmpty()
    }

    operator fun contains(card: Card): Boolean {
        return card in cards
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
