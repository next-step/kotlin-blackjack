package blackjack.domain

object Dealer {
    private val deck = Deck()

    fun giveCard(): Card? = deck.provideCard(deck.shuffled())
}
