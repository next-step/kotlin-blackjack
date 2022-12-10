package blackjack.domain.player

@JvmInline
value class Players(private val values: List<Player>) {
    init {
        require(values.size >= MINIMUM_NUMBER_OF_PLAYERS) { "플레이어는 2명 이상이어야 합니다." }
        require(values.distinct().size == values.size) { "플레이어는 중복될 수 없습니다." }
    }

    companion object {
        private const val MINIMUM_NUMBER_OF_PLAYERS = 2
    }
}
