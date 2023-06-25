package next.step.blackjack.domain.player

@JvmInline
value class PlayerNames(private val names: Set<PlayerName>) {

    fun names(): Set<String> = names.map { it.name() }.toSet()

    companion object {
        fun of(names: Set<PlayerName>): PlayerNames = PlayerNames(names)
    }
}