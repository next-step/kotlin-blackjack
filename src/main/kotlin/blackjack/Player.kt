package blackjack

class Player(val name: String, val playerDeck: PlayerDeck = PlayerDeck()) {

    fun draw(card: Card) {
        playerDeck.addCard(card)
    }
}
