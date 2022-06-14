package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.Participant
import blackjack.domain.Player

object ResultView {

    fun gameResult(gameResult: GameResult) {
        println()
        println("### 최종 수익")
        gameResult.allParticipant.forEach {
            printResult(it)
        }
    }

    private fun printResult(participant: Participant) {
        println("${participant.name} : ${participant.earnAmount}")
    }
}
