package blackjack.controller

import blackjack.adapter.BlackjackInputAdapter
import blackjack.domain.BlackjackGame
import blackjack.domain.HitStayChoice
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.dto.PlayersResponse
import blackjack.dto.SinglePlayerResponse
import blackjack.view.OutputView

class BlackjackGameController(
    private val inputAdapter: BlackjackInputAdapter,
    private val outputView: OutputView,
) {
    fun getPlayersNames(): List<PlayerName> {
        return inputAdapter.fetchPlayerNames()
    }

    fun announceInitialPlayersCards(blackJackGame: BlackjackGame) {
        val playersResponse = PlayersResponse(blackJackGame.players)
        outputView.printInitialPlayersCards(playersResponse)
    }

    fun playGame(blackJackGame: BlackjackGame) {
        blackJackGame.players.forEach { player ->
            playTurnForPlayer(player, blackJackGame)
        }
    }

    private fun playTurnForPlayer(
        player: Player,
        blackJackGame: BlackjackGame,
    ) {
        while (player.isDrawable()) {
            if (!processPlayerChoice(player, blackJackGame)) {
                break
            }
        }
        if (!player.isDrawable()) {
            outputView.printPlayerCannotDrawCard(SinglePlayerResponse(player))
        }
    }

    private fun processPlayerChoice(
        player: Player,
        blackJackGame: BlackjackGame,
    ): Boolean {
        val moreCardChoice = inputAdapter.fetchMoreCard(player.getName())
        return when (moreCardChoice) {
            HitStayChoice.HIT -> {
                blackJackGame.drawCard(player)
                outputView.printSinglePlayerCards(SinglePlayerResponse(player))
                true
            }

            HitStayChoice.STAY -> {
                outputView.printSinglePlayerCards(SinglePlayerResponse(player))
                false
            }
        }
    }

    fun announceResult(blackJackGame: BlackjackGame) {
        val playersResponse = PlayersResponse(blackJackGame.players)
        outputView.printPlayResult(playersResponse)
    }
}
