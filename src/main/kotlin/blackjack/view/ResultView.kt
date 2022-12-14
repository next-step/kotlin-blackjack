package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object ResultView {
    fun printInitialStatus(players: Players) {
        printPlayersName(players)
        printPlayersStatus(players)
    }

    fun printResult(players: Players) {
        println()
        players.values.forEach {
            println("${it.name} 카드: ${it.cards} - 결과: ${it.cards.getScore()}")
        }
    }

    private fun printPlayersName(players: Players) {
        val names = players.values.joinToString(", ") { it.name }

        println("\n$names 에게 2장의 카드를 나누었습니다.")
    }

    private fun printPlayersStatus(players: Players) {
        players.values.forEach { printPlayerStatus(it) }
    }

    fun printPlayerStatus(player: Player) {
        println("${player.name} 카드: ${player.cards}")
    }
}
