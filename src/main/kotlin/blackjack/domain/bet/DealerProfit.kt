package blackjack.domain.bet

class DealerProfit(profits: List<PlayerProfit>) {
    val profit: Profit = Profit(profits.sumOf { it.profit.value } * -1)
}
