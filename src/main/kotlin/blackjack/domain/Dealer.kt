package blackjack.domain

class Dealer(private val deck: Deck) {

    init {
        deck.shuffle()
    }

    fun dealCard(): Card = deck.draw()
}
