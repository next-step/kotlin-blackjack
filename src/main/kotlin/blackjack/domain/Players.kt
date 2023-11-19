package blackjack.domain

class Players(
    private val player: List<Player>
) {

    init {
        validate()
    }

    private fun validate() {
        require(player.size >= MIN_PLAYER_COUNT) {
            "플레이어는 최소 ${MIN_PLAYER_COUNT}명 이상이어야 합니다."
        }
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 2
    }
}
