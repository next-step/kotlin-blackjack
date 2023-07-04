package blackjack.game

import blackjack.dealer.Dealer
import blackjack.player.Player

class GameEvaluator {
    fun evaluate(dealer: Dealer, players: List<Player>): GameResult {
        val matchResult = mutableMapOf<String, Result>()
        matchResult[Dealer.DEALER_NAME] = Result()

        if (dealer.totalValue > BlackjackGame.BLACK_JACK_SCORE) {
            awardWinsToAllPlayers(players, matchResult)
        } else {
            evaluateScores(dealer, players, matchResult)
        }

        return GameResult(matchResult)
    }

    private fun awardWinsToAllPlayers(players: List<Player>, matchResult: MutableMap<String, Result>) {
        players.forEach { player ->
            val result = matchResult.getOrPut(player.name) { Result() }
            matchResult[player.name] = result.addWin()
        }
    }

    private fun evaluateScores(dealer: Dealer, players: List<Player>, matchResult: MutableMap<String, Result>) {
        var dealerResult = matchResult[dealer.name]!!
        val dealerScore = dealer.totalValue

        players.forEach { player ->
            var playerResult = matchResult.getOrPut(player.name) { Result() }
            val playerScore = player.totalValue

            when {
                playerScore > BlackjackGame.BLACK_JACK_SCORE -> {
                    playerResult = playerResult.addLose()
                    dealerResult = dealerResult.addWin()
                }
                dealerScore > BlackjackGame.BLACK_JACK_SCORE -> {
                    playerResult = playerResult.addWin()
                    dealerResult = dealerResult.addLose()
                }
                playerScore > dealerScore -> {
                    playerResult = playerResult.addWin()
                    dealerResult = dealerResult.addLose()
                }
                playerScore == dealerScore -> {
                    playerResult = playerResult.addDraw()
                    dealerResult = dealerResult.addDraw()
                }
                else -> {
                    playerResult = playerResult.addLose()
                    dealerResult = dealerResult.addWin()
                }
            }
            matchResult[player.name] = playerResult
        }
        matchResult[dealer.name] = dealerResult
    }
}
