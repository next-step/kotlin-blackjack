package blackjack.domain.result

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Players

class GameResultGenerator(val dealer: Dealer, val players: Players) {
    fun getDealerResult(): Map<Result, Int> {
        val matchResults = getMatchResults()
        return matchResults
            .map { it.ofDealer() }
            .groupingBy { it.result }
            .eachCount()
    }

    fun getDealerEarningAmount(): Double {
        return getMatchResults()
            .sumOf {
                it.ofDealer().earningAmount
            }
    }

    fun getMatchResults(): List<MatchResult> {
        return players.map { MatchResult(it, dealer) }
    }
}
