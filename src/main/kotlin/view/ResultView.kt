package view

import model.BlackJackGameStatusInfo
import model.Player

object ResultView {
    fun printPlayerInfo(blackJackGameStatusInfo: BlackJackGameStatusInfo) {
        println("${blackJackGameStatusInfo.playerNames().joinToString { it.value }}에게 ${blackJackGameStatusInfo.playerCardCount()}장의 카드를 나누었습니다.")
    }

    fun printCardInfo(players: List<Player>) {
        players.forEach {
            printCard(it)
            println()
        }
        println()
    }

    fun printCardCardReceive(player: Player) {
        printCard(player)
        println()
    }

    fun printCard(player: Player) {
        print("${player.name.value}카드 :${player.cards.joinToString { card -> card.toString() }}")
    }

    fun printResult(players: List<Player>) {
        println()
        players.forEach { player ->
            printCard(player)
            print(" - 결과 : ${player.score()}")
            println()
        }
    }
}
