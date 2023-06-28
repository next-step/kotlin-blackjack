package blackjack.view.output

import blackjack.domain.BlackjackGameResult
import blackjack.domain.DealerResult
import blackjack.domain.PlayerResult

class GamePlayersResultOutputView(gameResult: BlackjackGameResult) {
    init {
        println("\n### 최종 수익")
        renderDealerMessage(gameResult.dealer)
        gameResult.players.results.forEach { renderPlayerMessage(it) }
    }

    private fun renderDealerMessage(result: DealerResult) {
        val revenue = result.dealer.revenue.value
        println("딜러: $revenue")
    }

    private fun renderPlayerMessage(result: PlayerResult) {
        val name = result.player.name.value
        val revenue = result.player.betAmount.revenue
        println("$name: $revenue")
    }
}
