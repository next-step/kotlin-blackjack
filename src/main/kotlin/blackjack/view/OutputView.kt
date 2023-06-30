package blackjack.view

import blackjack.domain.Player

class OutputView : Output {

    override fun printFirstDeal(users: List<String>) {
        println()
        println("${users.joinToString { it.trim() }}에게 2장의 카드를 나누었습니다.")
    }

    override fun printPlayerCards(player: Player) {
        val message = player.cards.get().joinToString(", ") {
            "${it.denomination.score}${it.shape.desc}"
        }
        println("${player.name}카드: $message")
    }

    override fun printResult(player: Player) {
        val message = player.cards.get().joinToString(", ") {
            "${it.denomination.score}${it.shape.desc}"
        }
        println("${player.name}카드: $message - 결과: ${player.getTotalScore()}")
    }
}
