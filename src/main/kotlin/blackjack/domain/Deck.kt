package blackjack.domain

class Deck(private var deck: Set<Card>) {

    constructor() : this(
        DEFAULT_DECK
    )

    val size: Int
        get() = deck.size

    fun giveCard(deck: Set<Card>): Card {
        deck.shuffled()
        val cardPicked = deck.first()
        deck.drop(1)
        return cardPicked
    }

    fun shuffled() = deck.shuffled().toSet()

    companion object {


        private val DEFAULT_DECK = Suit.values().flatMap { cardPackOfSuit(it) }.toSet()


        private fun cardPackOfSuit(suit: Suit): Set<Card> {
            return Denomination.values().map { denomination -> Card(Pair(suit,denomination)) }.toSet()
        }
    }
}
