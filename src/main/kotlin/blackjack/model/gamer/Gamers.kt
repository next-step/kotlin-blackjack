package blackjack.model.gamer

import blackjack.model.Rule
import blackjack.model.trump.Deck

class Gamers private constructor(private val gamers: Set<Gamer>) : Set<Gamer> by gamers {
    constructor(gamers: List<Gamer>) : this(gamers.toSet())

    constructor(gamerNames: List<String>, deck: Deck) : this(gamerNames.map { Player(deck, it) })

    fun countWin(opponent: Gamer, rule: Rule): Int {
        return gamers.filter { it.isWin(opponent, rule) }.count()
    }

    fun countLose(opponent: Gamer, rule: Rule): Int {
        return gamers.filter { it.isLose(opponent, rule) }.count()
    }
}
