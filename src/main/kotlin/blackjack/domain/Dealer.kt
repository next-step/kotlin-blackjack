package blackjack.domain

class Dealer(private val deck: Deck = Deck()) {

    fun giveCard(): Card? = deck.provideCard(deck.shuffled())
}
