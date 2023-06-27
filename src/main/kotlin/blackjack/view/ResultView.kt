package blackjack.view

import blackjack.player.Player

object ResultView {

    fun printPlayerList(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        players.map { printPlayerStatus(it) }
    }

    fun printPlayerStatus(player: Player) {
        println("${player.name}카드: ${player.displayHand()}")
    }

    fun printFinalPlayerStatus(players: List<Player>) {
        println()
        players.forEach { player ->
            println("${player.name}카드: ${player.displayHand()} - 결과: ${player.getTotalValue()}")
        }
    }
}
