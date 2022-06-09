package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.GameResult
import blackjack.domain.Participant

object ResultView {
    fun gameResult(blackJackGame: BlackJackGame) {
        println()
        val gameResult = blackJackGame.getGameResult()
        println("### 최종 승패")
        gameResult.forEach {
            printResult(it)
        }
    }

    private fun printResult(participant: Participant) {
        var result = ""
        GameResult.values().forEach { gameResult ->
            participant.gameResults.count { it == gameResult }.takeIf { it != ZERO }
                ?.let { result = "$result$it${gameResult.getDisplayName()} " }
        }
        println("${participant.name} : $result")
    }

    private fun GameResult.getDisplayName(): String {
        return when (this) {
            GameResult.WIN -> "승"
            GameResult.LOSE -> "패"
            GameResult.DRAW -> "무"
        }
    }

    private const val ZERO = 0
}
