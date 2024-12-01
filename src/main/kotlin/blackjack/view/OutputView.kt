package blackjack.view

import blackjack.entity.Card
import blackjack.entity.Dealer
import blackjack.entity.Hand
import blackjack.entity.Participants
import blackjack.entity.Player
import blackjack.entity.PlayerResult

class OutputView {
    fun printInitialHands(participants: Participants) {
        val dealer = participants.dealer
        println("${dealer.name}와 ${participants.players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        println("${dealer.name}: ${formatHand(dealer.hand)}")
        participants.players.forEach {
            println("${it.name}: ${formatHand(it.hand)}")
        }
        println()
    }

    fun printPlayerHand(player: Player) {
        println("${player.name}카드: ${formatHand(player.hand)}\n")
    }

    fun printPlayerBusted(player: Player) {
        println("${player.name}님의 카드 합이 21을 초과했습니다. 턴을 종료합니다.\n")
    }

    fun printDealerDrawCard(dealer: Dealer) {
        println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다.\n")
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
