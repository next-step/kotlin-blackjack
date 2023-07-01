package blackjack.view

import blackjack.dealer.Dealer
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

    fun printFinalPlayerStatus(players: List<Player>) {
        println()
        players.forEach { player ->
            println("${player.name}카드: ${player.displayHand} - 결과: ${player.totalValue}")
        }
    }
}
