package blackjack

class GamePlayers(
    val players: List<GamePlayer>
) {
    val count = players.size

    companion object {
        const val PLAYER_NAME_DELIMITER = ","

        fun valueOf(playerNames: String): GamePlayers {
            return playerNames.split(PLAYER_NAME_DELIMITER)
                .map(::GamePlayer)
                .let { GamePlayers(it) }
        }
    }
}
