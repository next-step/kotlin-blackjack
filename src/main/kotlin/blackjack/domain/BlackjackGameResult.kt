package blackjack.domain

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
        return calculatePlayerProfits().values.sumOf { -it }
    }

    fun calculatePlayerProfits(): Map<Player, Int> {
        return players.associateWith { player ->
            val betAmount = player.bettingMoney
            calculateProfit(player, betAmount)
        }
    }

    private fun calculateProfit(
        player: Player,
        betAmount: BettingMoney,
    ): Int {
        return when (result[player]) {
            GameMatchResult.WIN -> calculateWinProfit(player, betAmount)
            GameMatchResult.LOSE -> betAmount.profitForLose()
            else -> 0
        }
    }

    private fun calculateWinProfit(
        player: Player,
        betAmount: BettingMoney,
    ): Int {
        if (player.isBlackjack()) {
            return betAmount.profitForBlackjackWin()
        }
        return betAmount.profitForWin()
    }
}
