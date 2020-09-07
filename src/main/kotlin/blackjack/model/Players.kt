package blackjack.model

class Players(private val players: List<Player>) {
    fun size() = players.size

    fun race(player: Player, cardDeck: CardDeck) {
        if (player.available()) {
            player.draw(cardDeck.drawCard())
        }
    }

    fun calculateResult(dealer: Dealer) {
        players.forEach { it.gameResult(dealer.score()) }
    }

    fun isPlayer(player: Player): Boolean {
        return players.contains(player)
    }

    operator fun get(index: Int): Player {
        return players[index]
    }

    override fun toString(): String {
        return players.joinToString { it.name }
    }
}
