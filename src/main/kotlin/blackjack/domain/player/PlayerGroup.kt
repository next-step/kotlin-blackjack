package blackjack.domain.player

class PlayerGroup(val players: List<Player>) : List<Player> by players {
    init {
        require(players.size <= MAX_COUNT) { "게임 최대 참가 인원은 $MAX_COUNT 입니다" }
    }

    companion object {
        private const val MAX_COUNT = 8
    }
}
