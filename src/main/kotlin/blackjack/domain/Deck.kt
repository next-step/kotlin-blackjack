package blackjack.domain

object Deck {

    fun provideCard(deck: MutableSet<Card>): Card {
        check(deck.isNotEmpty()) { "Deck has no card" }
        val card = deck.random()
        deck.remove(card)
        return card
    }

    fun cardPackOfSuit(suit: Suit): Set<Card> {
        return CardScore.values().map { cardScore -> Card(Pair(cardScore, suit)) }.toSet()
    }
}
