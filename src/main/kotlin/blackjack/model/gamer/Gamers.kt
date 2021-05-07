package blackjack.model.gamer

class Gamers(private val gamers: Set<Gamer>) : Set<Gamer> by gamers {
    constructor(gamers: List<Gamer>) : this(gamers.toSet())

    constructor(vararg sources: PlayerBuildSource) : this(sources.map { Player(it) })
}
