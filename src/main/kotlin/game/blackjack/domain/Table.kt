package game.blackjack.domain

class Table(
    private val players: Players,
    private val getAction: (name: String) -> Boolean,
    private val showPlayerCard: (player: Player) -> Unit,
) {

    fun init(): List<Player> = players.init(INIT_CARD_COUNT)

    fun distribute(): List<Player> = players.distribute(getAction, showPlayerCard)

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
