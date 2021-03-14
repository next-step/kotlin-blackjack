package blackjack.ui

import blackjack.domain.Player

object CardView {

    fun printCardsOf(players: List<Player>) {
        val names = players.joinToString(", ") { it.name }
        println("${names}에게 2장의 나누었습니다.")
        for (player in players) {
            printCardsOf(player)
        }
        println()
    }

    fun printCardsOf(player: Player) {
        println("${player.name}: ${player.cardNames.joinToString()}")
    }

    fun printResult(player: Player, point: Int) {
        println("${player.name}: ${player.cardNames.joinToString()} - 결과: ${point}")
    }
}
