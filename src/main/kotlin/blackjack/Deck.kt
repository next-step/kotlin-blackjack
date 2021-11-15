package blackjack

class Deck private constructor(private val cards: Cards) {

    val size: Int = cards.size

    fun isEmpty(): Boolean = size == 0

    fun peek(): Card? = cards.peek()

    fun draw(): Deck = Deck(cards.draw())

    companion object {
        fun shuffled(): Deck = Denomination.values()
            .asSequence()
            .flatMap { denomination -> Suit.values().map { suit -> Card(denomination, suit) } }
            .shuffled()
            .toList()
            .let { Deck(Cards(it)) }

        fun empty(): Deck = Deck(Cards.empty())
    }
}
