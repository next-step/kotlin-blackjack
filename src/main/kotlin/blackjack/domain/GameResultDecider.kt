package blackjack.domain

import java.util.EnumMap

object GameResultDecider {
    fun decide(players: Players, dealer: Dealer): GameResults {
        val playerGameResults: List<PlayerGameResult> = players.map {
            PlayerGameResult(it.name, it versus dealer)
        }
        val dealerGameResults: EnumMap<GameResult, Int> = EnumMap(GameResult::class.java)
        playerGameResults.forEach {
            val dealerGameResult = it.gameResult.opposite()
            dealerGameResults[dealerGameResult] = dealerGameResults.getOrDefault(dealerGameResult, 0) + 1
        }
        return GameResults(playerGameResults, dealerGameResults)
    }
}
