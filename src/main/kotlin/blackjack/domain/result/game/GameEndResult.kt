package blackjack.domain.result.game

import blackjack.domain.batting.BetBoard
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore
import blackjack.domain.result.Result
import blackjack.domain.result.distribution.DealEndResult

data class GameEndResult(
    val playerResults: List<PlayerGameResult>,
    val dealerResult: DealerGameResult,
) : Result() {

    val dealerHand: Hand = dealerResult.dealer.hand

    val dealerScore: HandScore = dealerResult.dealer.score

    val dealerProfit: Profit = dealerResult.profit

    companion object {
        fun of(dealEndResult: DealEndResult, betBoard: BetBoard): GameEndResult {
            val playerResults = dealEndResult.players.value.map { player ->
                PlayerGameResult.of(player, betBoard)
            }
            val dealerResult = DealerGameResult.of(dealEndResult.dealer, betBoard)
            return GameEndResult(playerResults, dealerResult)
        }
    }
}
