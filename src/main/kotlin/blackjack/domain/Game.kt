package blackjack.domain

class Game(players: List<Player>, private val deck: Deck) {
    private val players = Players(players)
    private var turn = 0

    fun result(): List<Player> {
        return (0 until players.size()).map { players.findPlayer(it) }
    }

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        const val MAXIMUM_GAME_SCORE = 21
        const val DEFAULT_CARD_AMOUNT = 2
    }
}
