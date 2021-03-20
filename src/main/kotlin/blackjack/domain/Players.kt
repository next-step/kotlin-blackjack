package blackjack.domain

class Players(
    playerNames: String
) {
    var players: List<Player>
        private set

    init {
        val playerNamesToken = tokenizePlayerNames(playerNames)
        validate(playerNamesToken)
        players = createPlayers(playerNamesToken)
    }

    private fun createPlayers(playerNamesToken: List<String>): List<Player> {
        return playerNamesToken.map {
            Player.from(PlayerName(it))
        }
    }

    private fun tokenizePlayerNames(playerNames: String): List<String> {
        require(playerNames.matches(Regex(PLAYER_NAMES_REGEX))) {
            "유효하지 않은 이름 형식입니다"
        }

        return playerNames.split(PLAYER_TOKEN_DELIMITER)
    }

    private fun validate(playerNamesToken: List<String>) {
        require(playerNamesToken.size in MINIMUM_COUNT_OF_PLAYER..MAXIMUM_COUNT_OF_PLAYER) {
            "게임에 참여할 수 있는 인원은 최소 ${MINIMUM_COUNT_OF_PLAYER}명에서 최대 ${MAXIMUM_COUNT_OF_PLAYER}명까지 입니다."
        }
    }

    companion object {
        private const val MINIMUM_COUNT_OF_PLAYER = 2
        private const val MAXIMUM_COUNT_OF_PLAYER = 8
        private const val PLAYER_TOKEN_DELIMITER = ','
        private const val PLAYER_NAMES_REGEX = "^\\w+(?:$PLAYER_TOKEN_DELIMITER ?\\w+)*\$"
    }
}
