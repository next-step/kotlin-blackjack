package blackjack.model.gamer

import blackjack.model.trump.Deck

class Gamers(private val gamers: Set<Gamer>) : Set<Gamer> by gamers {
    constructor(gamers: List<Gamer>) : this(gamers.toSet())

    constructor(sources: List<PlayerBuildSource>, deck: Deck) : this(sources.map { (name, betAmount) -> Player(deck, name, betAmount) })
}
