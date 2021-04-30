package blackjack.model.gamer

import blackjack.model.BetMoney
import blackjack.model.trump.Deck

class Gamers(private val gamers: Set<Gamer>) : Set<Gamer> by gamers {
    constructor(gamers: List<Gamer>) : this(gamers.toSet())

    constructor(namesAndBets: List<Pair<String, Int>>, deck: Deck) : this(namesAndBets.map { (name, betAmount) -> Player(deck, name, BetMoney(betAmount)) })
}
