package blackjack.controller

import blackjack.adapter.BlackjackInputAdapter
import blackjack.domain.BlackJackGame
import blackjack.domain.HitStayChoice
import blackjack.domain.PlayerName
import blackjack.dto.PlayerResponse
import blackjack.view.OutputView

class BlackjackGameController(
    private val inputAdapter: BlackjackInputAdapter,
    private val outputView: OutputView,
) {
    fun getPlayersNames(): List<PlayerName> {
        return inputAdapter.fetchPlayerNames()
    }

    fun announceInitialPlayersCards(blackJackGame: BlackJackGame) {
        val playerResponse = PlayerResponse(blackJackGame.players)
        outputView.printInitialPlayersCards(playerResponse)
    }

    fun playGame(blackJackGame: BlackJackGame) {
        blackJackGame.players.forEach { player ->
            while (true) {
                if (player.isDrawable()) {
                    val moreCardChoice = inputAdapter.fetchMoreCard(player.getName())
                    if (moreCardChoice == HitStayChoice.HIT) {
                        blackJackGame.drawCard(player)
                    } else {
                        outputView.printSinglePlayerCards(player.getName(), player.displayHand())
                        break
                    }
                    outputView.printSinglePlayerCards(player.getName(), player.displayHand())
                    continue
                }
                outputView.printPlayerCannotDrawCard(player.getName(), player.displayHand())
                break
            }
        }
    }

    fun announceResult(blackJackGame: BlackJackGame) {
        val playerResponse = PlayerResponse(blackJackGame.players)
        outputView.printPlayResult(playerResponse)
    }
}
