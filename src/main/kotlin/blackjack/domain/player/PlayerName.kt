package blackjack.domain.player

@JvmInline
value class PlayerName(val name: String) {
    override fun toString() = name
}
