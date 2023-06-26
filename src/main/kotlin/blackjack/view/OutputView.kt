package blackjack.view

import blackjack.domain.Player

object OutputView {

    fun printFirstDeal(users: List<String>) {
        println()
        println("${users.joinToString { it.trim() }}에게 2장의 카드를 나누었습니다.")
    }

    fun printPlayerCards(player: Player) {
        val message = player.cards.joinToString(", ") {
            "${it.denomination.score}${it.shape.desc}"
        }
        println("${player.name}카드: $message")
    }

    fun printResult(player: Player) {
        val message = player.cards.joinToString(", ") {
            "${it.denomination.score}${it.shape.desc}"
        }
        println("${player.name}카드: $message - 결과: ${player.getTotalScore()}")
    }
}
