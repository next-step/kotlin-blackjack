package game.blackjack.domain

class Table(
    private val players: List<Player>,
    private val getAction: (name: String) -> Boolean,
    private val showPlayerCard: (player: Player) -> Unit,
) {

    private val dealer = Dealer()

    fun init(): List<Player> {
        players.forEach {
            repeat(INIT_CARD_COUNT) { _ -> it.receive(dealer.drawCard()) }
        }
        return players
    }

    fun distribute(): List<Player> {
        players.forEach {
            while (it.canReceive()) {
                it.determine(getAction(it.name))
                if (it.canReceive()) {
                    it.receive(dealer.drawCard())
                }
                showPlayerCard(it)
            }
        }
        return players
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
