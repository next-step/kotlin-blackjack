package blackjack.domain

class Deck(deck: List<Card>) {
    private val deck: ArrayDeque<Card> = ArrayDeque(deck)

    fun pop(): Card = deck.removeLast()

    fun popMany(count: Int): List<Card> {
        require(deck.count() >= count) { "Not enough cards are in deck!" }

        return (1..count).map { deck.removeLast() }
    }

    companion object {
        fun forBlackjack(): Deck {
            return Deck(
                deck = CardShape.values().map { cardShape ->
                    CardNumber.values().map { cardNumber -> Card(number = cardNumber, shape = cardShape) }
                }.flatten().shuffled()
            )
        }
    }
}
