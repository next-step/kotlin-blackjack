package view

import model.PlayInfo
import model.Player

object ResultView {
    fun printPlayerInfo(playInfo: PlayInfo) {
        println("${playInfo.playerNames().joinToString { it.value }}에게 ${playInfo.playerCardCount()}장의 카드를 나누었습니다.")
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
