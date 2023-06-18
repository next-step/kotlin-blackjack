package blackjack.domain.player

@JvmInline
value class Players(private val players: List<Player>) : List<Player> by players {

    init {
        require(value = size in LIMIT_PLAYERS) {
            "참여 가능한 플레이어 범위가 아닙니다. 범위 : $LIMIT_PLAYERS, 참여한 플레이어 수 : $size"
        }
    }

    companion object {
        private val LIMIT_PLAYERS: IntRange = 2..8
    }
}
