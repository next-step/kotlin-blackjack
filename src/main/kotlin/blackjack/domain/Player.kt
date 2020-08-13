package blackjack.domain

class Player(val name: String) {
    var hands = Hands()
        private set

    fun drawCard(deck: Deck) {
        hands += deck.draw()
    }
}
