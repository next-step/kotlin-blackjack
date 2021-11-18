package blackjack.model

@JvmInline
value class Players private constructor(private val players: List<Player>) {

    fun toNames(): Names = players.map { it.name }.let(Names::of)

    fun receiveAll(
        count: Int = Int.MAX_VALUE,
        next: () -> Card?,
    ): Players {
        val limit = Array(players.size) { count }
        return receiveWhile(
            next = { index, _ -> takeIf { limit[index]-- > 0 }?.let { next() } }
        )
    }

    fun receiveWhile(
        next: (Player) -> Card?,
        onReceive: (Player) -> Unit
    ): Players = receiveWhile(next = { _, player -> next(player) }, onReceive)

    private fun receiveWhile(
        next: (Int, Player) -> Card?,
        onReceive: (Player) -> Unit = { }
    ): Players = players.mapIndexed { index, player ->
        player.receiveWhile(
            next = { next(index, player) },
            onReceive = { onReceive(player.copy(cards = it)) }
        ) as Player
    }.let(::Players)

    fun toList(): List<Player> = players

    companion object {
        fun from(names: Names): Players = names.toList()
            .map { Player.from(it, Cards.empty()) }
            .let(::Players)
    }
}
