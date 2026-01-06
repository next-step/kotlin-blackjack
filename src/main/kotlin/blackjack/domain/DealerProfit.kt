package blackjack.domain

class DealerProfit(
    playerProfits: List<PlayerProfit>,
) : Profit {
    override val name: String = "딜러"
    override val profit: Double = playerProfits.sumOf { -it.profit }
}
