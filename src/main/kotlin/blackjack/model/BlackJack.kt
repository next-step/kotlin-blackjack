package blackjack.model

class BlackJack(val players: Players, cardDeck: CardDeck) {
    private val cardDeck = cardDeck

    fun firstTurn() {
        repeat(players.size()) {
            players[it].draw(cardDeck.drawCard())
            players[it].draw(cardDeck.drawCard())
        }
    }

    fun race(player: Player) {
        players.race(player, cardDeck)
    }

    fun winner(): Players {
        return players.winner()
    }
}
