package blackjack.view

import blackjack.domain.BlackjackResult
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

    fun printDealerTurn(addedCard: Boolean) {
        if (addedCard) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        } else {
            println("딜러는 17이상이라 카드를 받지 않았습니다.")
        }
    }

    fun printBlackjackResult(blackjackResult: BlackjackResult) {
        println("## 최종 승패")
        println("딜러: ${blackjackResult.dealerWin}승 ${blackjackResult.dealerLose}패")
        blackjackResult.playerResult.forEach { playerResult ->
            println("${playerResult.player.name}: ${if (playerResult.isWin) "승" else "패"}")
        }
    }
}
