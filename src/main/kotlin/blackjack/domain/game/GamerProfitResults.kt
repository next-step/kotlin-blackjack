package blackjack.domain.game

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Players

data class GamerProfitResults(
    val dealerProfitResult: DealerProfitResult,
    val playerProfitResults: List<PlayerProfitResult>,
) {

    init {
        require(dealerProfitResult.profit == playerProfitResults.totalProfit() * -1) {
            "dealer profit and players total profit are inversely related. " +
                "dealer: ${dealerProfitResult.profit}, players: ${playerProfitResults.totalProfit()}"
        }
    }

    companion object {

        fun create(
            players: Players,
            dealer: Dealer,
        ): GamerProfitResults {
            val playerProfitResults = players.value.map { player -> PlayerProfitResult.create(player, dealer) }
            val dealerProfitResult = DealerProfitResult.create(playerProfitResults)
            return GamerProfitResults(
                dealerProfitResult = dealerProfitResult,
                playerProfitResults = playerProfitResults,
            )
        }
    }
}
