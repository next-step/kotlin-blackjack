package blackjack.domain

data class Deck(private var cards: Set<Card>) {
    val size: Int
        get() = cards.size

    constructor() : this(DEFAULT_SHUFFLED_DECK)

    fun provideCard(): Card {
        if (cards.isEmpty()) {
            cards = DEFAULT_SHUFFLED_DECK
        }
        val cardPicked = cards.first()
        cards = cards.drop(1).toSet()
        return cardPicked
    }

    companion object {
        private val DEFAULT_DECK = Suit.values().flatMap { cardPackOfSuit(it) }.toSet()
        private val DEFAULT_SHUFFLED_DECK = DEFAULT_DECK.shuffled().toSet()

        private fun cardPackOfSuit(suit: Suit): Set<Card> {
            return CardScore.values().map { cardScore -> Card(Pair(cardScore, suit)) }.toSet()
        }
    }
}
