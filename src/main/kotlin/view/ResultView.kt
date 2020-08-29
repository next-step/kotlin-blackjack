package view

import model.AbstractPlayer
import model.BlackJackGameStatusInfo
import model.GameResultStatus

object ResultView {
    fun printPlayerInfo(blackJackGameStatusInfo: BlackJackGameStatusInfo) {
        println("${blackJackGameStatusInfo.playerNames().joinToString { it.value }}에게 ${blackJackGameStatusInfo.playerCardCount()}장의 카드를 나누었습니다.")
    }

    fun printCardInfo(players: List<AbstractPlayer>) {
        players.forEach {
            printCard(it)
            println()
        }
        println()
    }

    fun printCardCardReceive(player: AbstractPlayer) {
        printCard(player)
        println()
    }

    fun printCard(player: AbstractPlayer) {
        print("${player.name.value}카드 :${player.cards.joinToString { card -> card.toString() }}")
    }

    fun printResult(players: List<AbstractPlayer>) {
        println()
        players.forEach { player ->
            printCard(player)
            print(" - 결과 : ${player.score()}")
            println()
        }
    }

    fun printReceiveCardDealer() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printWinner(gameResultStatusList: List<GameResultStatus>) {
        println()
        println("## 최종 승패")
        gameResultStatusList.forEach { gameResultStatus ->
            print("${gameResultStatus.playerName.value}: ")
            if (gameResultStatus.win > 0) {
                print("${gameResultStatus.win} 승 ")
            }
            if (gameResultStatus.draw > 0) {
                print("${gameResultStatus.draw} 무 ")
            }
            if (gameResultStatus.lose > 0) {
                print("${gameResultStatus.lose} 패 ")
            }
            println()
        }
    }
}
