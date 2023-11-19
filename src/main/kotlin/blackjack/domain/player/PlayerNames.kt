package blackjack.domain.player

@JvmInline
value class PlayerNames(
    val value: List<PlayerName>,
) {
    init {
        require(value.size == 2) {
            "두 명의 플레이어 이름이 필요합니다"
        }
    }

    companion object {
        fun from(names: List<String>): PlayerNames =
            names.map(::PlayerName).let(::PlayerNames)
    }
}
