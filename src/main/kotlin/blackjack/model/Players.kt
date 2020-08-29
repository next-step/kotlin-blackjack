package blackjack.model

class Players(players: List<Player>, dealer: Dealer) {
    private val players: MutableList<Player> = players.toMutableList()
    val dealer: Dealer = dealer.apply {
        this@Players.players.add(0, this)
    }

    fun size() = players.size

    fun dealerExceptionSize() = players.filter { it.name != DEALER_NAME }.size

    fun race(player: Player, cardDeck: CardDeck) {
        if (player.available()) {
            player.draw(cardDeck.drawCard())
        }
    }

    fun calculateResult(dealer: Dealer) {
        players.forEach { it.gameResult(dealer.score()) }
    }

    operator fun get(index: Int): Player {
        return players[index]
    }

    override fun toString(): String {
        return players.joinToString { it.name }
    }
}
