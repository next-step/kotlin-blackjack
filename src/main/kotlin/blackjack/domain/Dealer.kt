package blackjack.domain

class Dealer(private val deck: Deck) {

    init {
        deck.shuffle()
    }

    fun dealCard(): Card {
        if (deck.isEmpty()) {
            deck.reset()
            deck.shuffle()
        }

        return deck.draw()
    }
}
