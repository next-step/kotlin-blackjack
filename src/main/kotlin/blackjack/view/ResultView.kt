package blackjack.view

import blackjack.domain.player.Player

class ResultView {
    fun printInitialState(playerList: List<Player>) {
        println("${playerList.joinToString(",") { it.name }} 에게 2 장의 카드 나누었습니다.")
        playerList.forEach { player ->
            printPlayer(player)
        }
    }

    fun printResult(playerList: List<Player>) {
        playerList.forEach { player ->
            printPlayer(player)
        }
    }

    fun printPlayer(player: Player) {
        println("${player.name}: ${player.hand}")
    }
}
