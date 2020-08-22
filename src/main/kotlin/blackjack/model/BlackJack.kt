package blackjack.model

class BlackJack(val players: Players) {
    private val cardDeck = CardDeck().apply { this.shuffle() }

    fun firstTurn() {
        repeat(players.size()) {
            players[it].draw(cardDeck.drawCard(), cardDeck.drawCard())
        }
    }

    fun race(player: Player) {
        players.race(player, cardDeck)
    }

    fun gameResult(dealerPoint: Int) {
        return players.gameResult(dealerPoint)
    }
}
