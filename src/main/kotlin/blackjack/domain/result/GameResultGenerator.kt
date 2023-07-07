package blackjack.domain.result

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Players

class GameResultGenerator(val dealer: Dealer, val players: Players) {
    fun getDealerResult(): Map<Result, Int> {
        val matchResults = getPlayersResult()
        return matchResults
            .map { it.ofDealer() }
            .groupingBy { it }
            .eachCount()
    }

    fun getPlayersResult(): List<MatchResult> {
        return players.map { MatchResult(it, dealer) }
    }
}
