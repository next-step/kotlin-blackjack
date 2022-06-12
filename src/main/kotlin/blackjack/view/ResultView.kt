package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.GameScore
import blackjack.domain.Participant

object ResultView {
    fun gameResult(gameResult: GameResult) {
        println()
        println("### 최종 승패")
        gameResult.allParticipant.forEach {
            printResult(it)
        }
    }

    private fun printResult(participant: Participant) {
        if (participant is Dealer) {
            println("${participant.name} : ${makeResult(participant.gameScore)}")
        } else {
            println("${participant.name} : ${makePlayerResult(participant.gameScore)}")
        }

    }

    private fun makePlayerResult(gameScore: GameScore): String {
        if (gameScore.win != ZERO) {
            return "승"
        }
        if (gameScore.draw != ZERO) {
            return "무"
        }
        if (gameScore.lose != ZERO) {
            return "패"
        }
        return ""
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
