package blackjack.domain.result

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Players

class GameResultGenerator(val dealer: Dealer, val players: Players) {
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
