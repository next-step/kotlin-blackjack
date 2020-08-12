package blackjack.domain

object Dealer {
    private val deck: MutableSet<Card> =
        Suit.values().flatMap { Deck.cardPackOfSuit(it) }.toMutableSet()

    fun giveCard(): Card = Deck.provideCard(deck)
}
