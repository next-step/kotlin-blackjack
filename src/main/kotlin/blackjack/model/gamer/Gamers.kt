package blackjack.model.gamer

import blackjack.model.Bet
import blackjack.model.Judge
import blackjack.model.Rule
import blackjack.model.trump.Deck

class Gamers private constructor(private val gamers: Set<Gamer>) : Set<Gamer> by gamers {
    constructor(gamers: List<Gamer>) : this(gamers.toSet())

    constructor(namesAndBets: List<Pair<String, Int>>, deck: Deck) : this(namesAndBets.map { (name, betAmount) -> Player(deck, name, Bet(betAmount)) })

    fun calculateRevenue(rule: Rule, opponent: Gamer): Bet {
        return gamers.fold(Bet.ZERO) { betSum, gamer ->
            betSum + Judge.calculateRevenue(rule.getScore(gamer.cards), rule.getScore(opponent.cards), gamer.bet)
        }
    }
}
