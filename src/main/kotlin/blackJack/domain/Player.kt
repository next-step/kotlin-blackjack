package blackJack.domain

class Player(
    name: String,
) : GamePlayer(name = name) {

    init {
        require(name.isNotEmpty()) { IS_PLAYER_NAME_BLACK }
    }

    companion object {
        fun of(playerName: String): Player {
            return Player(playerName)
        }

        private const val IS_PLAYER_NAME_BLACK = "해당 플레이어 이름이 빈값입니다."
    }
}
