package blackjack.model.player

class Players(
    val players: List<Player>
) {

    init {
        validateNotEmpty(players)
    }

    private fun validateNotEmpty(players: List<Player>) {
        require(players.isNotEmpty()) { "플레이어는 1명 이상이어야 합니다." }
    }

    companion object {
        fun from(names: List<String>) = names.map { Player.from(it) }
    }
}
