package blackjack.domain

class Result(
    dealer: Dealer,
    players: List<Player>,
) {
    val playerProfits: List<PlayerProfit> = players.map { PlayerProfit(it.name, it.profit(dealer)) }

    val dealerProfit: DealerProfit = DealerProfit(playerProfits)
}
