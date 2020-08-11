package blackjack.domain

object Dealer {
    fun giveCard(): Card = Deck.provideCard()
}
