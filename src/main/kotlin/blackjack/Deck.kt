package blackjack

data class Deck(private var deck: Set<Card>) {

    constructor() : this(
        deck = createAllCard()
    )

    fun size(): Int? {
        return deck?.size
    }

    fun popDeck() {
        deck?.shuffled()
    }

    companion object {
        private val initialDeck = setOf<Card>()

        fun createAllCard(): Set<Card> {

            return Suit.values().flatMap { suit -> testDenomination(suit) }.toSet()

        }

        private fun testDenomination(suit: Suit): Set<Card> {
            return Denomination.values().map { denomination -> Card(Pair(denomination, suit)) }.toSet()
        }
    }
}
