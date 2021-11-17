package blackjack.model

@JvmInline
value class Players private constructor(private val players: List<Player>) {

    fun toNames(): Names = players.map { it.name }.let(Names::of)

    fun receiveWhile(
        limit: Int = Int.MAX_VALUE,
        onDraw: (Player) -> Card?
    ): Players = players
        .map { player -> player.receiveWhile(limit) { onDraw(player) } }
        .let(::Players)

    fun toList(): List<Player> = players

    companion object {
        fun from(names: Names): Players = names.toList()
            .map { Player(it, Cards.empty()) }
            .let(::Players)
    }
}
