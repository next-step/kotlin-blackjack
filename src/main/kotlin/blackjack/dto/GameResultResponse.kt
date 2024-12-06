package blackjack.dto

import blackjack.domain.BlackjackGameResult
import blackjack.domain.GameMatchResult
import blackjack.domain.Player

class GameResultResponse(private val gameResult: BlackjackGameResult) {
    fun toFormattedStringGameResult(): String {
        val dealerGameResults = calculateDealerResults()
        val formattedDealerResult = formatDealerGameResults(dealerGameResults)

        val playerGameResults = gameResult.extractPlayerGameResult()
        val formattedPlayerResults = formatPlayerResults(playerGameResults)

        return "$formattedDealerResult\n$formattedPlayerResults"
    }

    private fun calculateDealerResults(): Triple<Int, Int, Int> {
        return gameResult.extractPlayerGameResult().values.fold(Triple(0, 0, 0)) { (wins, draws, losses), gameResult ->
            when (gameResult) {
                GameMatchResult.WIN -> Triple(wins, draws, losses + 1)
                GameMatchResult.LOSE -> Triple(wins + 1, draws, losses)
                GameMatchResult.DRAW -> Triple(wins, draws + 1, losses)
            }
        }
    }

    private fun formatDealerGameResults(results: Triple<Int, Int, Int>): String {
        val (wins, draws, losses) = results
        val resultStrings = mutableListOf<String>()

        if (wins > 0) resultStrings.add("$wins 승")
        if (draws > 0) resultStrings.add("$draws 무")
        if (losses > 0) resultStrings.add("$losses 패")

        return "딜러: " + resultStrings.joinToString(" ")
    }

    private fun formatPlayerResults(playerGameResults: Map<Player, GameMatchResult>): String {
        return playerGameResults.entries.joinToString("\n") { (player, gameMatchResult) ->
            "${player.getName()}: ${gameMatchResult.result}"
        }
    }
}
