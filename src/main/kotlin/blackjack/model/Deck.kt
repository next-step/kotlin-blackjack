package blackjack.model

class Deck private constructor(private val cards: Cards) {

    fun isEmpty(): Boolean = cards.isEmpty()

    fun isNotEmpty(): Boolean = !isEmpty()

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
