package blackjack.model

class BlackJack(val players: List<Player>) {
    private val cardDeck = CardDeck()

    fun firstTurn() {
        players.forEach {
            it.pickCard(cardDeck.pickCard())
            it.pickCard(cardDeck.pickCard())
        }
    }

    fun race(player: Player) {
        if (player.isAvailable) {
            player.pickCard(cardDeck.pickCard())
        }
    }

    fun winner(): List<Player> {
        return players.filter { it.isWinner }
    }
}
