package blackjack.view

import blackjack.domain.participant.Player

object ResultView {
    fun printResult(players: List<Player>) {
        println()
        players.forEach { player -> println("${PlayerView.parsePlayerInfoToString(player)} - 결과: ${player.score()}") }
    }
}
