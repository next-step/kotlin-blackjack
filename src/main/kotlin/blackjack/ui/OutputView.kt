package blackjack.ui

import blackjack.application.dto.PlayerResult
import blackjack.application.dto.PlayerResults
import blackjack.application.dto.PlayerStatus
import blackjack.application.dto.PlayerStatuses
import blackjack.domain.player.vo.Name

object OutputView {
    fun printGameReady(names: List<Name>) {
        newline()
        println("${names.joinToString { it.value }}에게 2장의 카드를 나누었습니다.")
    }

    fun printStatuses(playerStatuses: PlayerStatuses) {
        playerStatuses.statuses.forEach { printStatus(it) }
    }

    fun printStatus(playerStatus: PlayerStatus) {
        println(
            "${playerStatus.name.value}카드: " +
                "${playerStatus.cards.joinToString { it.denomination.description + it.suit.description }}"
        )
    }

    fun printResults(playerResults: PlayerResults) {
        playerResults.results.forEach { printResult(it) }
    }

    private fun printResult(playerStatus: PlayerResult) {
        println(
            "${playerStatus.name.value}카드: " +
                "${playerStatus.cards.joinToString { it.denomination.description + it.suit.description }} " +
                "- 결과 ${playerStatus.score.value}"
        )
    }

    private fun newline(): Unit = println()
}
