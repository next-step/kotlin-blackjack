package blackjack.view

import blackjack.business.participants.Dealer
import blackjack.business.participants.PlayerResult
import blackjack.business.participants.Players

object GameResultOutputHandler {
    fun print(
        dealer: Dealer,
        players: Players
    ) {
        println()
        println("## 최종 승패")
        val dealerResult = dealer.getDealerResult(players)
        val result = dealerResult.map { "${it.value}${it.key.message}" }
        println("딜러: ${result.joinToString(", ")}")
        players.forEachPlayer { printPlayerResult(dealer.getPlayerResult(it)) }
    }

    private fun printPlayerResult(playerResult: PlayerResult) {
        println("${playerResult.name}: ${playerResult.result.message}")
    }
}
