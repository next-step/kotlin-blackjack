package blackjack.model.gamer

import blackjack.model.Bet
import blackjack.model.trump.Deck

class Gamers private constructor(private val gamers: Set<Gamer>) : Set<Gamer> by gamers {
    constructor(gamers: List<Gamer>) : this(gamers.toSet())

    constructor(namesAndBets: List<Pair<String, Int>>, deck: Deck) : this(namesAndBets.map { (name, betAmount) -> Player(deck, name, Bet(betAmount)) })
}
