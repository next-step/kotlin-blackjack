package blackjack.domain.player

class Players(
    private val players: List<Player>
) {
    init {
        require(players.size > 1) { "플레이어는 최소 2명 이상이어야 합니다." }
    }
}
