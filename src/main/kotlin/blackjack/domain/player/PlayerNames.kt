package blackjack.domain.player

@JvmInline
value class PlayerNames(
    val value: List<PlayerName>,
) {
    init {
        require(value.distinct().size == value.size) { "플레이어는 각기 다른 이름이어야 합니다" }
    }

    companion object {
        fun from(names: List<String>): PlayerNames =
            names.map(::PlayerName).let(::PlayerNames)
    }
}
