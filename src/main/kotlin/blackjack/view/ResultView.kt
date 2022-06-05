package blackjack.view

import blackjack.domain.player.Player

object ResultView {
    fun printResult(players: List<Player>) {
        println()
        players.forEach { player -> println("${PlayerView.parsePlayerInfoToString(player)} - 결과: ${player.score()}") }
    }
}
