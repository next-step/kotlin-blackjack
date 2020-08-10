package blackjack.view

import blackjack.model.Player

object ResultView {

    fun printPreGame(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        players.map { printPlayerHaveCard(it) }
    }

    fun printPlayerHaveCard(player: Player) {
        println("${player.name}카드: ${player.myReceivedCard.joinToString()}")
    }

    fun printResult(player: Player, rank: Int) {
        println("${player.name}카드: ${player.myReceivedCard.joinToString()} - 결과: $rank")
    }
}
