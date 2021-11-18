package blackjack.model

@JvmInline
value class Gamers private constructor(private val gamers: List<Gamer>) {

    fun filterPlayers(): Gamers = Gamers(gamers.filterIsInstance<Player>())

    fun toNames(): Names = gamers.map { it.name }.let(Names::from)

    fun toList(): List<Gamer> = gamers

    operator fun plus(player: Gamer): Gamers = Gamers(gamers + player)

    fun receiveAll(
        count: Int = Int.MAX_VALUE,
        next: () -> Card?,
    ): Gamers {
        val limit = Array(gamers.size) { count }
        val hasNext: (Int) -> Boolean = { index -> limit[index]-- > 0 }
        return receiveWhile(
            next = { index, _ -> takeIf { hasNext(index) }?.let { next() } }
        )
    }

    fun receiveWhile(
        next: (Gamer) -> Card?,
        onReceive: (Gamer) -> Unit
    ): Gamers = receiveWhile(next = { _, gamer -> next(gamer) }, onReceive)

    private fun receiveWhile(
        next: (Int, Gamer) -> Card?,
        onReceive: (Gamer) -> Unit = { }
    ): Gamers = gamers.mapIndexed { index, gamer ->
        gamer.receiveWhile(
            next = { next(index, gamer) },
            onReceive = { cards -> onReceive(gamer.copy(cards = cards)) }
        )
    }.let(::Gamers)

    companion object {
        fun empty(): Gamers = Gamers(emptyList())

        fun players(names: Names): Gamers = names.toList()
            .map { name -> Gamer.player(name) }
            .let(::Gamers)
    }
}
