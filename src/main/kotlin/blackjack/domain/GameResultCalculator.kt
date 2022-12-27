package blackjack.domain

import blackjack.model.GameResult
import blackjack.model.PlayerGameResult
import blackjack.model.PlayerGameResults

class GameResultCalculator : ResultCalculator {

    override fun calculate(dealer: Dealer, players: Players): PlayerGameResults =
        calculatePlayerResult(dealer, players)

    private fun calculatePlayerResult(dealer: Dealer, players: Players): PlayerGameResults {
        var dealerProfit = 0.0
        val playerProfits = players.value.map { player ->
            val result = calculateGameResult(dealer, player)
            val playerProfit = calculatePlayProfit(result, player)
            dealerProfit += calculateDealerProfit(result, player)

            PlayerGameResult.Player(player.name, playerProfit)
        }
        return PlayerGameResults(listOf(PlayerGameResult.Dealer(dealer.name, dealerProfit)) + playerProfits)
    }

    private fun calculatePlayProfit(result: GameResult, player: Player): Double =
        when (result) {
            GameResult.WIN, GameResult.BLACKJACK -> {
                player.play.profit(player.bet.value)
            }

            GameResult.PUSH_WITH_BLACKJACK -> player.bet.value
            GameResult.LOSE -> {
                -player.bet.value
            }

            GameResult.PUSH -> 0.0
        }.toDouble()

    private fun calculateDealerProfit(result: GameResult, player: Player): Double =
        when (result) {
            GameResult.WIN, GameResult.BLACKJACK -> {
                -player.play.profit(player.bet.value)
            }

            GameResult.LOSE -> {
                player.bet.value
            }

            else -> 0.0
        }.toDouble()

    private fun calculateGameResult(dealer: Dealer, player: Player): GameResult {
        val playerSum = player.play.score()
        val dealerSum = dealer.play.score()
        return when {
            dealer.play.blackjack && player.play.blackjack -> GameResult.PUSH_WITH_BLACKJACK
            player.play.blackjack -> GameResult.WIN
            (dealer.play.bust && !player.play.bust) ||
                isPlayerWinWithStay(dealer.play.stay, player.play.stay, playerSum > dealerSum) -> GameResult.WIN

            isPlayerEvenWithStay(dealer.play.stay, player.play.stay, playerSum == dealerSum) -> GameResult.PUSH
            else -> GameResult.LOSE
        }
    }

    private fun isPlayerWinWithStay(dealerStay: Boolean, playerStay: Boolean, win: Boolean): Boolean {
        return dealerStay && playerStay && win
    }

    private fun isPlayerEvenWithStay(dealerStay: Boolean, playerStay: Boolean, even: Boolean): Boolean {
        return dealerStay && playerStay && even
    }
}

interface ResultCalculator {
    fun calculate(dealer: Dealer, players: Players): PlayerGameResults
}
