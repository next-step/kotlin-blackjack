package blackjack

class BlackjackDealer(private val deck: CardDeck = CardDeck.createDeck()) {
    fun startTheGame(players: List<Player>): List<Player> {
        return players.map(::drawTwice)
    }

    private fun drawTwice(player: Player): Player {
        var playerTemp = player
        repeat(2) { playerTemp = playerTemp.addCardToHand(this.deck.draw()) }
        return playerTemp
    }
}
