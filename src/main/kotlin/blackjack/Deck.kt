package blackjack

data class Deck(private var deck: Cards = Cards()) {

    constructor() : this(
        deck = createAllCard()
    )

    fun size(): Int {
        return deck.size()
    }

    companion object {
        private val initialDeck = Cards()

        fun createAllCard(): Cards {

            for (name in Suit.values()) {
                testDenomination(name).map { card -> initialDeck.addCard(card) }
            }
            return initialDeck
        }

        private fun testDenomination(suit: Suit): List<Card> {
            return Denomination.values().map { denomination -> Card(Pair(denomination, suit)) }
        }
    }
}