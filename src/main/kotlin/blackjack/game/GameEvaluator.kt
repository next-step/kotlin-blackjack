package blackjack.game

import blackjack.dealer.Dealer
import blackjack.player.Player

class GameEvaluator {
    fun evaluate(dealer: Dealer, players: List<Player>): GameResult {
        val matchResult = mutableMapOf<String, Result>()
        matchResult["딜러"] = Result()

        if (dealer.totalValue > BlackjackGame.BUST_SCORE) {
            awardWinsToAllPlayers(players, matchResult)
        } else {
            evaluateScores(dealer, players, matchResult)
        }

        return GameResult(matchResult)
    }

    private fun awardWinsToAllPlayers(players: List<Player>, matchResult: MutableMap<String, Result>) {
        players.forEach { player ->
            matchResult.getOrPut(player.name) { Result() }.addWin()
        }
    }

    private fun evaluateScores(dealer: Dealer, players: List<Player>, matchResult: MutableMap<String, Result>) {
        val dealerResult = matchResult[dealer.name]!!
        val dealerScore = dealer.totalValue

        players.forEach { player ->
            val playerResult = matchResult.getOrPut(player.name) { Result() }
            val playerScore = player.totalValue

            when {
                playerScore > BlackjackGame.BUST_SCORE -> {
                    playerResult.addLose()
                    dealerResult.addWin()
                }
                dealerScore > BlackjackGame.BUST_SCORE -> {
                    playerResult.addWin()
                    dealerResult.addLose()
                }
                playerScore > dealerScore -> {
                    playerResult.addWin()
                    dealerResult.addLose()
                }
                playerScore == dealerScore -> {
                    playerResult.addDraw()
                    dealerResult.addDraw()
                }
                else -> {
                    playerResult.addLose()
                    dealerResult.addWin()
                }
            }
        }
    }
}
