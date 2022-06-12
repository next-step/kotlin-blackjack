package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.GameScore
import blackjack.domain.Participant

object ResultView {
    fun gameResult(blackJackGame: BlackJackGame) {
        println()
        println("### 최종 승패")
        blackJackGame.participants.forEach {
            printResult(it)
        }
    }

    private fun printResult(participant: Participant) {
        println("${participant.name} : ${makeResult(participant.gameScore)}")
    }

    private fun makeResult(gameScore: GameScore): String {
        return buildString {
            if (gameScore.win != ZERO) {
                this.append("${gameScore.win}승")
            }
            if (gameScore.draw != ZERO) {
                this.append("${gameScore.draw}무")
            }
            if (gameScore.lose != ZERO) {
                this.append("${gameScore.lose}패")
            }
        }
    }

    private const val ZERO = 0
}
