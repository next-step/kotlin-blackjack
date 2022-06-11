package blackjack.domain

class Player(val name: String) {
    var hands = Hands(PlayingCards.empty())
        private set

    fun receive(playingCards: PlayingCards) {
        hands += playingCards
    }

    fun stay() {
        hands = hands.stay()
    }

    fun isReceivable(): Boolean = hands.isReceivable()
}
