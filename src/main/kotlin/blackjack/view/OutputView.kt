package blackjack.view

import blackjack.entity.Card
import blackjack.entity.Dealer
import blackjack.entity.GameResult
import blackjack.entity.Hand
import blackjack.entity.Participants
import blackjack.entity.Player

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

    fun printPlayerResults(
        playerName: String,
        playerHand: Hand,
        score: Int,
    ) {
        println("${playerName}카드: ${formatHand(playerHand)} - 결과: $score")
    }

    fun printGameResult(gameResults: List<GameResult>) {
        println("\n## 최종 승패")
        gameResults.forEach { result ->
            when (result.player) {
                is Dealer -> {
                    val resultText =
                        buildString {
                            if (result.wins > 0) append("${result.wins}승 ")
                            if (result.loses > 0) append("${result.loses}패 ")
                            if (result.draws > 0) append("${result.draws}무")
                        }.trim()
                    println("딜러: $resultText")
                }

                else -> {
                    val outcome =
                        when {
                            result.wins > 0 -> "승"
                            result.loses > 0 -> "패"
                            else -> "무"
                        }
                    println("${result.player.name}: $outcome")
                }
            }
        }
    }

    private fun formatHand(hand: Hand): String {
        return hand.cards
            .joinToString(", ") { it.name() }
    }

    private fun Card.name() = "${rank.displayName}${suit.displayName}"
}
