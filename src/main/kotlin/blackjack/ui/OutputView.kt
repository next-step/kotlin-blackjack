package blackjack.ui

import blackjack.domain.participants.Player

object OutputView {
    fun printAllPlayerCards(players: List<Player>) {
        val names = players.joinToString { it.name }
        println("$names 에게 2장의 카드를 나누었습니다.")
        for(player in players) {
            println("${player.name} 카드 : ${player.showPlayersCard()}")
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name} 의 카드 : ${player.showPlayersCard()}")
    }

    fun printResult(players: List<Player>) {
        println("\n ----------- 결산 -----------")
        for (player in players) {
            println("${player.name} 카드 : ${player.showPlayersCard()} - 합계 ${player.getPlayerScore()}")
        }
    }
}