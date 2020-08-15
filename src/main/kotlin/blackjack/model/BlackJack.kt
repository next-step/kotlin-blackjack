package blackjack.model

class BlackJack(val players: Players) {
    private val cardDeck = CardDeck()

    fun firstTurn() {
        players.firstTurn(cardDeck)
    }

    fun race(player: Player) {
        players.race(player, cardDeck)
    }

    fun winner(): Players {
        return players.winner()
    }
}
