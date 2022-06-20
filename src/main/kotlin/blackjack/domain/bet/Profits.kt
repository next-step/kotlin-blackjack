package blackjack.domain.bet

import blackjack.domain.player.PlayerResult

class Profits private constructor(val dealer: DealerProfit, val players: List<PlayerProfit>) {
    companion object {
        fun of(dealer: PlayerResult, players: List<PlayerResult>): Profits {
            val playerProfits = players.map { PlayerProfit(it.player.name, ProfitCalculator.calculate(it, dealer)) }
            val dealerProfit = DealerProfit(playerProfits)

            return Profits(dealerProfit, playerProfits)
        }
    }
}
