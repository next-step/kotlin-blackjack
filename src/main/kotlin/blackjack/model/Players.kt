package blackjack.model

class Players(players: List<Player>) {
    private val players = players

    fun firstTurn(cardDeck: CardDeck) {
        players.forEach {
            it.pickCard(cardDeck.pickCard())
            it.pickCard(cardDeck.pickCard())
        }
    }

    fun race(player: Player, cardDeck: CardDeck) {
        if (player.isAvailable) {
            player.pickCard(cardDeck.pickCard())
        }
    }

    fun winner(): Players {
        return Players(players.filter { it.isWinner })
    }
}
