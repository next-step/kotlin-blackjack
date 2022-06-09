package game.blackjack.domain

class Dealer {
    private val deck = Deck()

    fun drawCard(): Card = deck.draw()
}
