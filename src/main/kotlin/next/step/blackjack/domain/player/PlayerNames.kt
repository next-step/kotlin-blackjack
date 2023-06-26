package next.step.blackjack.domain.player

@JvmInline
value class PlayerNames(private val names: Set<PlayerName>) : Set<PlayerName> by names {
    init {
        require(names.isNotEmpty()) { "반드시 한 명 이상의 플레이어 이름이 있어야 합니다." }
    }

    companion object {
        fun of(names: Set<PlayerName>): PlayerNames = PlayerNames(names)
    }
}
