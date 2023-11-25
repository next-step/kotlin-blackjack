package blackjack.domain

import blackjack.domain.state.Blackjack

class ProfitCalculator(private val inputNameAndBets: Map<String, BettingMoney>) {

    fun getPlayerProfit(player: Player, dealer: Dealer): Int {
        val bettingMoney = inputNameAndBets[player.name] ?: throw IllegalArgumentException(PLAYER_BETTING_MONEY_NOT_FOUND_ERROR_MESSAGE)

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return 0
        }
        if (player.isBlackjack()) {
            return (bettingMoney.value * Blackjack.PROFIT_RATE).toInt()
        }
        return calculatePlayerProfit(dealer, player)
    }

    private fun calculatePlayerProfit(dealer: Dealer, player: Player): Int {
        val bettingMoney = inputNameAndBets[player.name] ?: throw IllegalArgumentException(PLAYER_BETTING_MONEY_NOT_FOUND_ERROR_MESSAGE)

        return when {
            dealer.isBust() -> bettingMoney.value
            player.isBust() -> -1 * bettingMoney.value

            player.state.getSum() > dealer.state.getSum() -> bettingMoney.value
            player.state.getSum() < dealer.state.getSum() -> -1 * bettingMoney.value
            else -> 0
        }
    }

    fun getDealerProfit(player: Player, dealer: Dealer): Int {
        return -getPlayerProfit(player, dealer)
    }

    companion object {
        private const val PLAYER_BETTING_MONEY_NOT_FOUND_ERROR_MESSAGE = "해당 플레이어의 베팅 금액을 찾을 수 없습니다."
    }
}
