package blackjack.domain

class Dealer(deck: List<Card>) {
    private val deck: ArrayDeque<Card> = ArrayDeque(deck)

    fun draw(): Card = deck.removeLast()

    fun drawMany(count: Int): List<Card> {
        require(deck.count() >= count) { "Not enough cards are in deck!" }

        return (1..count).map { deck.removeLast() }
    }

    companion object {
        fun withFullDeck(): Dealer {
            return Dealer(
                deck = CardShape.values().map { cardShape ->
                    CardNumber.values().map { cardNumber -> Card(number = cardNumber, shape = cardShape) }
                }.flatten().shuffled()
            )
        }
    }
}
