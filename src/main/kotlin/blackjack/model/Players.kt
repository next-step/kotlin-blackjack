package blackjack.model

class Players(players: List<Player>) {
    private val players: MutableList<Player> = players.toMutableList()
    val dealer = Dealer().apply { this@Players.players.add(0, this) }

    fun size() = players.size

    fun race(player: Player, cardDeck: CardDeck) {
        if (player.available()) {
            player.draw(cardDeck.drawCard())
        }
    }

    fun gameResult(dealerPoint: Int) {
        players.forEach { it.gameResult(dealerPoint, playerMinGap()) }
    }

    private fun playerMinGap(): Int {
        return players.minBy { it.score() }!!.score()
    }

    operator fun get(index: Int): Player {
        return players[index]
    }

    override fun toString(): String {
        return players.joinToString { it.name }
    }
}
