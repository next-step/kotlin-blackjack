package blackjack.view

import blackjack.domain.player.Player

class ResultView {
    fun printResult(playerList: List<Player>) {
        playerList.forEach { player ->
            printPlayer(player)
        }
    }

    private fun printPlayer(player: Player) {
        println("${player.name}: ${player.hand}")
    }
}
