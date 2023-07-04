package blackjack.view

import blackjack.dealer.Dealer
import blackjack.game.Result
import blackjack.player.Player

object ResultView {

    fun printInitialDistribute(dealer: Dealer, players: List<Player>) {
        println("딜러와 ${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        println("딜러 카드: ${dealer.displayHand}")
    }

    fun printPlayerList(players: List<Player>) {
        players.map { printPlayerStatus(it) }
    }

    fun printPlayerStatus(player: Player) {
        println("${player.name}카드: ${player.displayHand}")
    }

    fun printFinalDealerStatus(dealer: Dealer) {
        println()
        println("딜러 카드: ${dealer.displayHand} - 결과: ${dealer.totalValue}")
    }

    fun printFinalPlayerStatus(players: List<Player>) {
        players.forEach { player ->
            println("${player.name}카드: ${player.displayHand} - 결과: ${player.totalValue}")
        }
    }
    fun printDealerStatus() {
        println()
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printMatchResult(matchResult: Map<String, Result>) {
        println()
        println("## 최종 승패")
        matchResult.forEach { (name, result) ->
            val resultStr = if (name == Dealer.DEALER_NAME) result.formatDealerResult() else result.formatPlayerResult()
            println("$name: $resultStr")
        }
    }
}
