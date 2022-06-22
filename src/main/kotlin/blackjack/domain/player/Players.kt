package blackjack.domain.player

@JvmInline
value class Players(
    private val players: List<Player>,
) {
    init {
        val distinctPlayers = players.toSet()
        require(players.size == distinctPlayers.size) { "플레이어의 이름이 중복되면 안됩니다." }
    }
}
