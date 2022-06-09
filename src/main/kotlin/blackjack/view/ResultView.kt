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
        GameResult.values().forEach { enum ->
            participant.gameResults.count { it == enum }.takeIf { it != ZERO }
                ?.let { result = "$result$it${enum.getDisplayName()} " }
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
