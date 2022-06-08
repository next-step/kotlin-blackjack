package game.blackjack.domain

class Table(
    val players: List<Player>,
    val getAction: (name: String) -> String,
    val showPlayerCard: (player: Player) -> Unit,
) {

    private val dealer = Dealer()

    fun start() {
        players.forEach {
            repeat(INIT_CARD_COUNT) { _ -> it.receive(dealer.drawCard()) }
        }
    }

    fun distribute() {
        players.forEach {
            while (it.canReceive()) {
                it.determine(getAction(it.name))
                if (it.canReceive()) {
                    it.receive(dealer.drawCard())
                }
                showPlayerCard(it)
            }
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 3
    }
}
