package blackjack.domain

class Player(val name: String) {
    var hands = Hands()

    fun drawCard(deck: Deck) {
        hands += deck.draw()
    }

    infix fun hit(card: Card) {
        hands += card
    }
}
