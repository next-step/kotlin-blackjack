package game.blackjack.domain

class Players(
    private val dealer: Dealer,
    val players: List<Player>,
) {
    fun init(initCount: Int): Players {
        listOf(dealer, *(players.toTypedArray())).forEach {
            repeat(initCount) { _ -> it.receive(dealer.drawCard()) }
        }
        return this
    }

    fun distribute(getAction: (name: String) -> Boolean, showPlayerCard: (player: Player) -> Unit): Players {
        (players + dealer).forEach {
            it.receiveUntilHit(getAction, showPlayerCard) { dealer.drawCard() }
        }

        players.forEach { it.record(dealer) }

        return this
    }

    fun forEach(action: (player: Player) -> Unit) = players.forEach { action(it) }

    fun forEachWithDealer(action: (player: Player) -> Unit) =
        listOf(dealer, *(players.toTypedArray())).forEach { action(it) }
}
