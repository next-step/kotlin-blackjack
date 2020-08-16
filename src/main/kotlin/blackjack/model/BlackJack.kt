package blackjack.model

class BlackJack(val players: Players, cardDeck: CardDeck) {
    private val cardDeck = cardDeck.apply {
        this.shuffle()
    }

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
