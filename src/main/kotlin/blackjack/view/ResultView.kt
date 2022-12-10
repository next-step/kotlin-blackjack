package blackjack.view

import blackjack.domain.Game
import blackjack.domain.Player

object ResultView {
    fun printInitialStatus() {
        printPlayersName(Game.players)
        printPlayersStatus(Game.players)
    }

    fun printResult() {
        println()
        Game.players.forEach {
            println("${it.name} 카드: ${it.cards} - 결과: ${it.cards.getScore()}")
        }
    }

    private fun printPlayersName(players: List<Player>) {
        val names = players.joinToString(", ") { it.name }

        println("\n$names 에게 2장의 카드를 나누었습니다.")
    }

    private fun printPlayersStatus(players: List<Player>) {
        players.forEach { printPlayerStatus(it) }
    }

    fun printPlayerStatus(player: Player) {
        println("${player.name} 카드: ${player.cards}")
    }
}
