package blackjack

class Player(val name: String, val cards: PlayerCards = PlayerCards()) {
    fun receive(card: PlayingCard) {
        cards.add(card)
    }
}
