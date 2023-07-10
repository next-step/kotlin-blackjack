package blackjack.domain.result

import blackjack.domain.users.Dealer
import blackjack.domain.users.Player
import kotlin.math.roundToInt

data class GameResults(
    val playerResults: List<PlayerResult>,
    val dealerResult: DealerResult,
) {
    companion object {
        fun from(players: List<Player>, dealer: Dealer): GameResults {
            val playerResults = players.map { player -> playerResult(player, dealer) }
            val finalRevenue = playerResults.sumOf { it.finalRevenue }.times(-1)
            val dealerResult = DealerResult(finalRevenue)
            return GameResults(playerResults, dealerResult)
        }

        fun ofDealerLose(players: List<Player>) = GameResults(
            players.map { PlayerResult(it.name, it.bettingAmount) },
            DealerResult(players.sumOf { it.bettingAmount }.times(-1))
        )

        private fun playerResult(player: Player, dealer: Dealer): PlayerResult {
            val finalRevenue = player.bettingAmount
            if (player.isBlackjack() && !dealer.isBlackjack()) {
                return PlayerResult(
                    player.name,
                    finalRevenue.times(1.5).roundToInt()
                )
            }

            if (player.isBlackjack() && dealer.isBlackjack()) {
                return PlayerResult(player.name, 0)
            }

            if (player.cardValues() in (dealer.cardValues() + 1)..21) {
                return PlayerResult(player.name, finalRevenue)
            }
            return PlayerResult(player.name, finalRevenue.times(-1))
        }
    }
}
