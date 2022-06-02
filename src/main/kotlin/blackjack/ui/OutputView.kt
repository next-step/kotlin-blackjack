package blackjack.ui

import blackjack.domain.player.Name
import blackjack.ui.dto.PlayerStatus

object OutputView {
    fun printGameReady(names: List<Name>) {
        newline()
        println("${names.joinToString { it.value }}에게 2장의 카드를 나누었습니다.")
    }

    fun printStatuses(statuses: List<PlayerStatus>) {
        statuses.forEach { printStatus(it) }
    }

    fun printStatus(playerStatus: PlayerStatus) {
        println(
            "${playerStatus.name.value}카드: " +
                "${playerStatus.cards.joinToString { it.denomination.description + it.suit.description }}"
        )
    }

    fun printResults(statuses: List<PlayerStatus>) {
        statuses.forEach { printResult(it) }
    }

    private fun printResult(playerStatus: PlayerStatus) {
        println(
            "${playerStatus.name.value}카드: " +
                "${playerStatus.cards.joinToString { it.denomination.description + it.suit.description }} " +
                "- 결과 ${playerStatus.score.value}"
        )
    }

    private fun newline(): Unit = println()
}
