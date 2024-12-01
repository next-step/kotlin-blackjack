package blackjack.view

import blackjack.entity.Card
import blackjack.entity.Hand
import blackjack.entity.Player
import blackjack.entity.PlayerResult
import blackjack.entity.Players

class OutputView {
    fun printInitialHands(players: Players) {
        println("${players.participants.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        players.participants.forEach {
            println("${it.name}: ${formatHand(it.hand)}")
        }
        println()
    }

    fun printPlayerHand(player: Player) {
        println("${player.name}카드: ${formatHand(player.hand)}")
    }

    fun printPlayerBusted(player: Player) {
        println("${player.name}님의 카드 합이 21을 초과했습니다. 턴을 종료합니다.")
    }

    fun printPlayerResults(results: List<PlayerResult>) {
        results.forEach { result ->
            println("${result.playerName}카드: ${formatHand(result.playHand)} - 결과: ${result.score}")
        }
    }

    private fun formatHand(hand: Hand): String {
        return hand.cards
            .joinToString(",") { it.name() }
    }

    private fun Card.name() = "${rank.displayName}${suit.displayName}"
}
