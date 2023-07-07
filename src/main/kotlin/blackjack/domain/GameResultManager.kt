package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.Status

object GameResultManager {
    fun calculate(dealer: Dealer, players: List<Player>): GameResultSummary {

        val playerResults: List<PlayerResult> = players.map { player ->
            PlayerResult(player, (profitRatio(dealer, player) * player.money.value).toInt())
        }

        return GameResultSummary(playerResults)
    }

    private fun profitRatio(dealer: Dealer, player: Player): Double {

        if (player.status == Status.BLACKJACK) {
            return if(dealer.blackjack()) 1.0 else 1.5
        }

        if (player.status == Status.BUST) {
            return -1.0
        }

        if (dealer.bust()) {
            return 1.0
        }

        return if(dealer.total() < player.total()) 1.0 else -1.0
    }
}

data class GameResultSummary(val playerResults: List<PlayerResult>) {
    fun dealerProfit(): Int = playerResults.sumOf { it.profit } * -1
}
data class PlayerResult(val player: Player, val profit: Int)
