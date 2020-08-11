package blackjack.domain

class Game(players: List<Player>) {
    private val _players = players
    val players: List<Player>
        get() = _players.toList()

    constructor(PlayerNames: String) : this(
        PlayerNames.split(PLAYER_NAMES_DELIMITER)
            .filterNot { it.isNullOrBlank() }
            .map { Player(it.trim()) }
    )

    fun setUp(): List<Player> {
        _players.forEach { player -> repeat(DEFAULT_CARD_AMOUNT) { player.draw(Dealer.giveCard()) } }
        return players.toList()
    }

    fun drawOnce(player: Player): Player {
        if (!player.hasScoreMoreThanMax()) {
            player.draw(Dealer.giveCard())
        }
        return player
    }

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        const val MAXIMUM_GAME_SCORE = 21
        const val DEFAULT_CARD_AMOUNT = 2
        const val REPLY_YES = "y"
        const val REPLY_NO = "n"
    }
}
