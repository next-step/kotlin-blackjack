package blackjack.domain

class Dealer {
    var deck = Deck()
        private set

    fun ready() {
        deck.ready()
    }

    fun dealCard(): Card {
        return deck.draw()
    }
}
