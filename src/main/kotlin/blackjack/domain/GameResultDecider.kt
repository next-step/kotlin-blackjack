package blackjack.domain

import java.util.EnumMap

object GameResultDecider {
    fun decide(participants: Participants): GameResults {
        val playerGameResults: List<PlayerGameResult> = participants.players.map {
            PlayerGameResult(it.name, it versus participants.dealer)
        }
        val dealerGameResults: EnumMap<GameResult, Int> = EnumMap(GameResult::class.java)
        playerGameResults.forEach {
            val dealerGameResult = it.gameResult.opposite()
            dealerGameResults[dealerGameResult] = dealerGameResults.getOrDefault(dealerGameResult, 0) + 1
        }
        return GameResults(playerGameResults, dealerGameResults)
    }
}
