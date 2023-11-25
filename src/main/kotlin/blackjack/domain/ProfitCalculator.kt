package blackjack.domain

class ProfitCalculator(private val inputNameAndBets: Map<String, BettingMoney>) {

    fun getPlayerProfit(player: Player, dealer: Dealer): Int {
        val bettingMoney = inputNameAndBets[player.name] ?: throw IllegalArgumentException()

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return 0
        }
        if (player.isBlackjack()) {
            return (bettingMoney.value * 1.5).toInt()
        }
        return calculatePlayerProfit(dealer, player)
    }

    private fun calculatePlayerProfit(dealer: Dealer, player: Player): Int {
        val bettingMoney = inputNameAndBets[player.name] ?: throw IllegalArgumentException()

        if (dealer.isBust()) {
            return bettingMoney.value
        }
        if (player.isBust()) {
            return -bettingMoney.value
        }

        if (player.hand.getSum() > dealer.hand.getSum()) {
            return bettingMoney.value
        }
        if (player.hand.getSum() < dealer.hand.getSum()) {
            return -bettingMoney.value
        }
        return 0
    }

    fun getDealerProfit(player: Player, dealer: Dealer): Int {
        return -getPlayerProfit(player, dealer)
    }
}
