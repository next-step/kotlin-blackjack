package blackjack.dto

import blackjack.domain.BlackjackGameResult

class GameResultResponse(private val gameResult: BlackjackGameResult) {
    fun toFormattedStringGameResult(): String {
        val playerGameResult = gameResult.extractPlayerGameResult()
        return gameResult.extractDealerGameResult() + "\n" +
            playerGameResult.entries.joinToString("\n") { (player, gameMatchResult) ->

                "${player.getName()}: ${gameMatchResult.result}"
            }
    }
}
