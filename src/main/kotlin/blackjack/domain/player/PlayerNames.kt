package blackjack.domain.player

@JvmInline
value class PlayerNames(
    val value: List<PlayerName>,
) {
    companion object {
        fun from(names: List<String>): PlayerNames =
            names.map(::PlayerName).let(::PlayerNames)
    }
}
