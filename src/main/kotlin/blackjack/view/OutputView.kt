package blackjack.view

import blackjack.model.Player
import blackjack.service.FinalScoreCalculator.finalScoreOf

object OutputView {
    fun printInitCards(players: List<Player>) {
        println()
        players.joinToString(",") { it.name }
            .also { println("${it}에게 각각 2장의 카드를 나누었습니다.") }
        players.forEach(::printPlayerCards)
        println()
    }

    fun printPlayerCards(player: Player) {
        println(cardDetailsOf(player))
    }

    fun printResult(players: List<Player>) {
        println()
        players.forEach {
            println("${cardDetailsOf(it)} - 결과: ${finalScoreOf(it)}")
        }
    }

    private fun cardDetailsOf(player: Player): String {
        val cardsString = player.joinToString(",") { it.getDisplayName() }
        return "${player.name}카드: $cardsString"
    }
}
