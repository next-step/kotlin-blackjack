package blackjack.ui

import blackjack.application.dto.BlackJackResult
import blackjack.application.dto.BlackJackRoundResults
import blackjack.application.dto.BlackjackResults
import blackjack.application.dto.BlackJackStatus
import blackjack.application.dto.BlackJackStatuses
import blackjack.domain.player.vo.Name

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

    fun printResults(blackjackResults: BlackjackResults) {
        blackjackResults.results.forEach { printResult(it) }
    }

    private fun printResult(playerStatus: BlackJackResult) {
        println(
            "${playerStatus.name.value}카드: " +
                "${playerStatus.cards.joinToString { it.denomination.description + it.suit.description }} " +
                "- 결과 ${playerStatus.score.value}"
        )
    }

    fun printBlackJackResult(blackJackRoundResults: BlackJackRoundResults) {
        blackJackRoundResults.blackJackRoundResults.forEach { println("${it.name.value}카드: ${it.roundResults}") }
        newline()
    }

    fun printDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        newline()
    }

    fun printDealerBust(blackJackRoundResults: BlackJackRoundResults) {
        println("딜러의 점수가 21점을 넘어 모든 플레이어가 승리했습니다.")
        blackJackRoundResults.blackJackRoundResults.forEach { println("${it.name.value}카드: ${it.roundResults}") }
        newline()
    }

    private fun newline(): Unit = println()
}
