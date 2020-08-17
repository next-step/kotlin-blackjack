package blackjack.domain

class Deck(private var deck: Set<Card>) {

    constructor() : this(
        DEFAULT_DECK
    )

    fun shuffled(): Set<Card> = deck.shuffled().toSet()

    fun provideCard(deck: Set<Card>): Card? {
        if (deck.isEmpty()) return null

        val shuffledDeck = deck.toMutableSet()
        val cardPicked = shuffledDeck.first()
        shuffledDeck.remove(cardPicked)
        this.deck = shuffledDeck.toSet()
        return cardPicked
    }

    companion object {
        private val DEFAULT_DECK = Suit.values().flatMap { cardPackOfSuit(it) }.toSet()

        private fun cardPackOfSuit(suit: Suit): Set<Card> {
            return CardScore.values().map { cardScore -> Card(Pair(cardScore, suit)) }.toSet()
        }
    }
}
