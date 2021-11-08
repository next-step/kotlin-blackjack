package blackjack.domain

data class Players(val players: List<Player>) {
    init {
        require(players.isNotEmpty()) { NUMBER_OF_PLAYER_SHOULD_BE_LARGER_THAN_ZERO }
    }

    fun getNames(): String {
        return players.joinToString { it.name }
    }

    companion object {
        private const val PLAYER_NAME_DELIMITER = ","
        private const val NUMBER_OF_PLAYER_SHOULD_BE_LARGER_THAN_ZERO = "적어도 한명의 플레이어가 존재해야 합니다."

        fun from(input: String): Players {
            return Players(buildPlayer(input.split(PLAYER_NAME_DELIMITER)))
        }

        private fun buildPlayer(names: List<String>) = names
            .map { Player(it.trim()) }
            .toList()
    }
}
