package blackJack.domain.player

class Player(
    val name: String,
    val status: PlayingAreaImpl = PlayingAreaImpl.of()
) : PlayingArea by status {
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
