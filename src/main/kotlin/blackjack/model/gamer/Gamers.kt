package blackjack.model.gamer

import blackjack.model.Bet
import blackjack.model.Judge
import blackjack.model.Rule
import blackjack.model.trump.Deck

class Gamers constructor(gamers: List<Gamer>) : Iterable<Gamer> {
    private val gamers = gamers.toList()

    val size: Int
        get() = gamers.size

    constructor(namesAndBets: List<Pair<String, Int>>, deck: Deck) : this(namesAndBets.map { (name, betAmount) -> Player(deck, name, Bet(betAmount)) })

    fun calculateRevenue(rule: Rule, opponent: Gamer): Bet {
        return gamers.fold(Bet.ZERO) { betSum, gamer ->
            betSum + Judge.calculateRevenue(rule.getScore(gamer.cards), rule.getScore(opponent.cards), gamer.bet)
        }
    }

    override fun iterator(): Iterator<Gamer> {
        return gamers.iterator()
    }
}
