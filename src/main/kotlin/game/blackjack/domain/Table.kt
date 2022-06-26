package game.blackjack.domain

class Table(
    private val dealer: Dealer,
    private val players: List<Player>,
    private val getAction: (name: String) -> Boolean,
    private val showPlayerCard: (player: Player) -> Unit,
) {

    fun init(): List<Player> {
        listOf(dealer, *(players.toTypedArray())).forEach {
            repeat(INIT_CARD_COUNT) { _ -> it.receive(dealer.drawCard()) }
        }
        return players
    }

    fun distribute(): List<Player> {
        (players + dealer).forEach {
            it.receiveUntilHit(getAction, showPlayerCard) { dealer.drawCard() }
        }

        return players
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
