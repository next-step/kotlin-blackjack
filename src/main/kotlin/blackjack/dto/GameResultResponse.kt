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

    private fun calculateDealerResults(): Map<GameMatchResult, Int> {
        return gameResult.extractPlayerGameResult()
            .values
            .groupingBy { it }
            .eachCount()
    }

    private fun formatDealerGameResults(results: Map<GameMatchResult, Int>): String {
        val resultStrings =
            listOfNotNull(
                results[GameMatchResult.WIN]?.let { "$it 패" },
                results[GameMatchResult.DRAW]?.let { "$it 무" },
                results[GameMatchResult.LOSE]?.let { "$it 승" },
            )
        return "딜러: " + resultStrings.joinToString(" ")
    }

    private fun formatPlayerResults(playerGameResults: Map<Player, GameMatchResult>): String {
        return playerGameResults.entries.joinToString("\n") { (player, gameMatchResult) ->
            "${player.getName().value}: ${gameMatchResult.result}"
        }
    }

    fun toFormattedStringPlayerProfit(): String {
        val dealerProfit = gameResult.calculateDealerProfit()
        val playerProfits =
            gameResult.calculatePlayerProfits().map { (player, profit) ->
                "${player.getName().value}: $profit"
            }
        return "딜러: $dealerProfit\n" + playerProfits.joinToString("\n")
    }
}
