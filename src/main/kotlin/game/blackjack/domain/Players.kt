package game.blackjack.domain

class Players(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun init(initCount: Int): List<Player> {
        listOf(dealer, *(players.toTypedArray())).forEach {
            repeat(initCount) { _ -> it.receive(dealer.drawCard()) }
        }
        return players
    }

    fun distribute(getAction: (name: String) -> Boolean, showPlayerCard: (player: Player) -> Unit): List<Player> {
        (players + dealer).forEach {
            it.receiveUntilHit(getAction, showPlayerCard) { dealer.drawCard() }
        }

        players.forEach { it.record(dealer) }

        return players
    }
}
