package blackjack.ui

import blackjack.application.dto.BlackJackScore
import blackjack.application.dto.BlackJackStatus
import blackjack.application.dto.BlackJackStatuses
import blackjack.application.dto.BlackJackWinningResults
import blackjack.application.dto.BlackjackScores
import blackjack.domain.participant.vo.Name

object OutputView {
    fun printGameReady(names: List<Name>) {
        newline()
        println("${names.joinToString { it.value }}에게 2장의 카드를 나누었습니다.")
    }

    fun printStatuses(blackJackStatuses: BlackJackStatuses) {
        blackJackStatuses.statuses.forEach { printStatus(it) }
    }

    fun printStatus(blackJackStatus: BlackJackStatus) {
        println(
            "${blackJackStatus.name.value}카드: " +
                "${blackJackStatus.cards.joinToString { it.denomination.description + it.suit.description }}"
        )
    }

    fun printResults(blackjackScores: BlackjackScores) {
        blackjackScores.scores.forEach { printResult(it) }
        newline()
    }

    private fun printResult(playerStatus: BlackJackScore) {
        println(
            "${playerStatus.name.value}카드: " +
                "${playerStatus.cards.joinToString { it.denomination.description + it.suit.description }} " +
                "- 결과 ${playerStatus.score.value}"
        )
    }

    fun printBlackJackResult(blackJackWinningResults: BlackJackWinningResults) {
        blackJackWinningResults.blackJackWinningResults.forEach {
            println(
                "${it.name.value}카드: ${it.winningScores.winCount} 승 " +
                    "${it.winningScores.loseCount} 패 ${it.winningScores.drawCount} 무"
            )
        }
        newline()
    }

    fun printDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        newline()
    }

    fun printDealerBust(blackJackWinningResults: BlackJackWinningResults) {
        println("딜러의 점수가 21점을 넘어 남아있는 플레이어가 승리했습니다.")
        blackJackWinningResults.blackJackWinningResults.forEach {
            println(
                "${it.name.value}카드: ${it.winningScores.winCount} 승 " +
                    "${it.winningScores.loseCount} 패 ${it.winningScores.drawCount} 무"
            )
        }
        newline()
    }

    private fun newline(): Unit = println()
}
