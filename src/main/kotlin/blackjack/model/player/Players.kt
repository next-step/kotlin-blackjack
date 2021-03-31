package blackjack.model.player

import blackjack.model.Rule
import blackjack.model.trump.Deck

class Players private constructor(private val players: Set<Player>) : Set<Player> by players {

    constructor(players: List<Player>) : this(players.toSet())

    constructor(playerNames: List<String>, deck: Deck) : this(playerNames.map { Player(deck, it) })

    fun countWin(opponent: Player, rule: Rule): Int {
        return players.filter { it.isWin(opponent, rule) }.count()
    }

    fun countLose(opponent: Player, rule: Rule): Int {
        return players.filter { it.isLose(opponent, rule) }.count()
    }
}
