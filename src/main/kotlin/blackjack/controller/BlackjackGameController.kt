package blackjack.controller

import blackjack.adapter.BlackjackInputAdapter
import blackjack.domain.BlackJackGame
import blackjack.domain.HitStayChoice
import blackjack.domain.Player
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
            playTurnForPlayer(player, blackJackGame)
        }
    }

    private fun playTurnForPlayer(
        player: Player,
        blackJackGame: BlackJackGame,
    ) {
        while (player.isDrawable()) {
            if (!processPlayerChoice(player, blackJackGame)) {
                break
            }
        }
        if (!player.isDrawable()) {
            outputView.printPlayerCannotDrawCard(player.getName(), player.displayHand())
        }
    }

    private fun processPlayerChoice(
        player: Player,
        blackJackGame: BlackJackGame,
    ): Boolean {
        val moreCardChoice = inputAdapter.fetchMoreCard(player.getName())
        return when (moreCardChoice) {
            HitStayChoice.HIT -> {
                blackJackGame.drawCard(player)
                outputView.printSinglePlayerCards(player.getName(), player.displayHand())
                true
            }

            HitStayChoice.STAY -> {
                outputView.printSinglePlayerCards(player.getName(), player.displayHand())
                false
            }
        }
    }

    fun announceResult(blackJackGame: BlackJackGame) {
        val playerResponse = PlayerResponse(blackJackGame.players)
        outputView.printPlayResult(playerResponse)
    }
}
