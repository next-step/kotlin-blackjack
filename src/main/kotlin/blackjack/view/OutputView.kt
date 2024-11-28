package blackjack.view

import blackjack.entity.Player
import blackjack.entity.PlayerResult
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

    fun printPlayerResults(results: List<PlayerResult>) {
        results.forEach { result ->
            println("${result.playerName}카드: ${result.handDescription} - 결과: ${result.score}")
        }
    }
}
