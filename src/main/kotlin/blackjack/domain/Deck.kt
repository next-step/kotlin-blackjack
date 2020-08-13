package blackjack.domain

class Deck(private var deck: Set<Card>) {

    constructor() : this(
        DEFAULT_DECK
    )

    fun shuffled(): Set<Card> {
        return deck.shuffled().toSet()
    }

    fun provideCard(deck: Set<Card>): Card {
        check(deck.isNotEmpty()) { "Deck has no card" }
        val shuffledDeck = deck.toMutableSet()
        val card = shuffledDeck.take(1)[0]
        shuffledDeck.remove(card)
        this.deck = shuffledDeck.toSet()
        return card
    }

    companion object {
        val DEFAULT_DECK = Suit.values().flatMap { cardPackOfSuit(it) }.toSet()

        private fun cardPackOfSuit(suit: Suit): Set<Card> {
            return CardScore.values().map { cardScore -> Card(Pair(cardScore, suit)) }.toSet()
        }
    }
}
