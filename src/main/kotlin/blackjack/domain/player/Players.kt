package blackjack.domain.player

class Players(val players: List<Player>) {
    init {
        require(players.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { PLAYERS_SIZE_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 6
        private const val PLAYERS_SIZE_EXCEPTION_MESSAGE = "블랙잭 플레이어는 1~6명 이어야 합니다."
    }
}
