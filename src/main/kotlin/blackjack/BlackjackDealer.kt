package blackjack

class BlackjackDealer(private var deck: CardDeck = CardDeck.createDeck()) {
    fun startTheGame(players: List<Player>) {
        players.forEach(::drawTwice)
    }

    private fun drawTwice(player: Player) {
        repeat(2) { player.addCardToHand(this.deck.draw()) }
    }

    fun shuffleCardDeck() {
        this.deck = this.deck.shuffle()
    }

    fun sendCard(player: Player) {
        player.addCardToHand(this.deck.draw())
    }
}
