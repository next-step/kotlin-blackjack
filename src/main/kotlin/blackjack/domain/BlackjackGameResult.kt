package blackjack.domain

import blackjack.domain.GameMatchResult.LOSE
import blackjack.domain.GameMatchResult.WIN

class BlackjackGameResult(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private val result: Map<Player, GameMatchResult> =
        players.associateWith { player ->
            player.compareWithDealer(dealer)
        }

    fun extractPlayerGameResult(): Map<Player, GameMatchResult> {
        return result
    }

    fun calculateDealerProfit(): Int {
        return players.sumOf { player ->
            val betAmount = player.bettingMoney.amount
            -calculateProfit(player, betAmount)
        }
    }

    fun calculatePlayerProfits(): Map<Player, Int> {
        return players.associateWith { player ->
            val betAmount = player.bettingMoney.amount
            calculateProfit(player, betAmount)
        }
    }

    private fun calculateProfit(
        player: Player,
        betAmount: Int,
    ): Int {
        return when (result[player]) {
            WIN -> betAmount
            LOSE -> -betAmount
            else -> 0
        }.let { if (player.isBlackjack() && result[player] == WIN) it + (0.5 * betAmount).toInt() else it }
    }
}
