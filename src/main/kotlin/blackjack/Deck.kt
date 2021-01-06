package blackjack

data class Deck(private var deck: Set<Card>) {

    constructor() : this(
        deck = createAllCard()
    )

    fun size(): Int {

        return deck.size ?: 0
    }

    fun popDeck(): Card? {
        if (deck.isEmpty()) {
            return null
        }
        val shuffleDeck = deck.shuffled().toMutableSet()
        val lastCard = deck.last()
        shuffleDeck.remove(lastCard)
        this.deck = shuffleDeck.toSet()
        return lastCard
    }

    companion object {

        private fun createAllCard(): Set<Card> {

            return Suit.values().flatMap { suit -> testDenomination(suit) }.toSet()
        }

        private fun testDenomination(suit: Suit): Set<Card> {
            return Denomination.values().map { denomination -> Card(Pair(denomination, suit)) }.toSet()
        }
    }
}
