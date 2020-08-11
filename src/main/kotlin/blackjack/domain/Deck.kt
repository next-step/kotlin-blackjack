package blackjack.domain

object Deck {
    private val deck: MutableSet<Card> =
        CardScore.values().flatMap { makeCardPair(it) }.toMutableSet()

    fun provideCard(): Card {
        val card = deck.random()
        deck.remove(card)
        return card
    }

    private fun makeCardPair(cardScore: CardScore): Set<Card> {
        return Suit.values().map { suit -> Card(Pair(cardScore, suit)) }.toSet()
    }
}
