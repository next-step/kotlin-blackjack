package blackjack.view

import blackjack.entity.Hand
import blackjack.entity.Player
import blackjack.entity.PlayerResult
import blackjack.entity.Players

class OutputView {
    fun printInitialHands(players: Players) {
        println("${players.participants.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        players.describeHands().forEach {
            println("${it.playerName}: ${formatHand(it.hand)}")
        }
        println()
    }

    fun printPlayerHand(player: Player) {
        println("${player.name}카드: ${formatHand(player.hand)}")
    }

    fun printPlayerResults(results: List<PlayerResult>) {
        results.forEach { result ->
            println("${result.playerName}카드: ${formatHand(result.playHand)} - 결과: ${result.score}")
        }
    }

    private fun formatHand(hand: Hand): String = hand.cards.joinToString(",") { it.describe() }
}
