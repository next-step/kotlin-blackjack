package blackjack.domain

import blackjack.model.GameResult
import blackjack.model.PlayerGameResult
import blackjack.model.PlayerGameResults

class GameResultCalculator : ResultCalculator {

    override fun calculate(dealer: Dealer, players: Players): PlayerGameResults {
        val playerResults = calculatePlayerResult(dealer, players)
        val dealerResult = calculateDealerResult(dealer, playerResults)
        return PlayerGameResults(listOf(dealerResult) + playerResults)
    }

    private fun calculatePlayerResult(dealer: Dealer, players: Players): List<PlayerGameResult> {
        val playerResults = mutableListOf<PlayerGameResult>()
        players.value.forEach { player ->
            val result = calculateGameResult(dealer, player)
            playerResults.add(PlayerGameResult.Player(player.name, result))
        }
        return playerResults
    }

    private fun calculateDealerResult(dealer: Dealer, playerResults: List<PlayerGameResult>): PlayerGameResult.Dealer =
        PlayerGameResult.Dealer(
            name = dealer.name,
            win = playerResults.count { it is PlayerGameResult.Player && it.gameResult == GameResult.LOSE },
            push = playerResults.count { it is PlayerGameResult.Player && it.gameResult == GameResult.PUSH },
            lose = playerResults.count { it is PlayerGameResult.Player && it.gameResult == GameResult.WIN },
        )

    private fun calculateGameResult(dealer: Dealer, player: Player): GameResult {
        val playerSum = player.play.score()
        val dealerSum = dealer.play.score()
        return when {
            dealer.play.blackjack && player.play.blackjack -> GameResult.PUSH
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
