package blackjack.domain

import blackjack.dto.BlackJackResult
import blackjack.dto.DealerResult
import blackjack.dto.PlayerResult
import blackjack.entity.BlackJack
import blackjack.entity.Game

object GameResultCalculator {
    fun calculateGameResult(game: Game): BlackJackResult {
        val dealerScore = game.dealer.getDealerBlackJack().getTotalCardValue()

        return if (dealerScore > BlackJack.BUST_LIMIT_VALUE) {
            dealerLoseResult(game)
        } else {
            compareGameResults(game, dealerScore)
        }
    }

    private fun dealerLoseResult(game: Game): BlackJackResult {
        val playerResults =
            game.players.map { player ->
                PlayerResult(
                    playerName = player.name,
                    winCount = 1,
                    loseCount = 0,
                    drawCount = 0,
                )
            }

        val dealerResult = DealerResult(dealerName = game.dealer.name, winCount = 0, loseCount = playerResults.size, drawCount = 0)

        return BlackJackResult(dealerResult, playerResults)
    }

    private fun compareGameResults(
        game: Game,
        dealerScore: Int,
    ): BlackJackResult {
        val playerResults =
            game.players.map { player ->
                val playerScore = player.getPlayerBlackJack().getTotalCardValue()
                when {
                    playerScore > BlackJack.BUST_LIMIT_VALUE ->
                        PlayerResult(
                            playerName = player.name,
                            winCount = 0,
                            loseCount = 1,
                            drawCount = 0,
                        )

                    playerScore > dealerScore ->
                        PlayerResult(
                            playerName = player.name,
                            winCount = 1,
                            loseCount = 0,
                            drawCount = 0,
                        )

                    playerScore == dealerScore ->
                        PlayerResult(
                            playerName = player.name,
                            winCount = 0,
                            loseCount = 0,
                            drawCount = 1,
                        )

                    else ->
                        PlayerResult(
                            playerName = player.name,
                            winCount = 0,
                            loseCount = 1,
                            drawCount = 0,
                        )
                }
            }

        val dealerWinCount = playerResults.count { it.loseCount > 0 }
        val dealerLoseCount = playerResults.count { it.winCount > 0 }
        val dealerDrawCount = playerResults.count { it.drawCount > 0 }

        val dealerResult =
            DealerResult(
                dealerName = game.dealer.name,
                winCount = dealerWinCount,
                loseCount = dealerLoseCount,
                drawCount = dealerDrawCount,
            )

        return BlackJackResult(dealerResult, playerResults)
    }
}
