package blackjack.game

import blackjack.BlackjackGame
import blackjack.dealer.Dealer
import blackjack.player.Player

object GameEvaluator {
    private val matchResult: MutableMap<String, Result> = mutableMapOf("딜러" to Result())

    fun evaluate(dealer: Dealer, players: List<Player>) {
        if (dealer.totalValue > BlackjackGame.BUST_SCORE) {
            awardWinsToAllPlayers(players)
        } else {
            evaluateScores(dealer, players)
        }
    }

    private fun awardWinsToAllPlayers(players: List<Player>) {
        players.forEach { player ->
            matchResult.getOrPut(player.name) { Result() }.addWin()
            matchResult["딜러"]!!.addLose()
        }
    }

    private fun evaluateScores(dealer: Dealer, players: List<Player>) {
        val dealerResult = matchResult["딜러"]!!
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
    val getMatchResult = matchResult
}
