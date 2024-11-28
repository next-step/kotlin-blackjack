package blackjack.view

import blackjack.entity.Player
import blackjack.entity.Players

class OutputView {
    fun printInitialHands(players: Players) {
        println("${players.participants.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        players.describeHands().forEach {
            println("${it.playerName}: ${it.hand}")
        }
        println()
    }

    fun printPlayerHand(player: Player) {
        println("${player.name}카드: ${player.describeHand()}")
    }
}
