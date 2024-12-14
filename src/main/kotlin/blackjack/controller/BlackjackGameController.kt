package blackjack.controller

import blackjack.adapter.BlackjackInputAdapter
import blackjack.domain.BettingMoney
import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.HitStayChoice
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.dto.GameResultResponse
import blackjack.dto.ParticipantsResponse
import blackjack.dto.SinglePlayerResponse
import blackjack.view.OutputView

class BlackjackGameController(
    private val inputAdapter: BlackjackInputAdapter,
    private val outputView: OutputView,
) {
    fun getPlayersNames(): List<PlayerName> {
        return inputAdapter.fetchPlayerNames()
    }

    fun getPlayerBettingMoneys(playerNames: List<PlayerName>): List<BettingMoney> {
        return playerNames.map { playerName ->
            BettingMoney(inputAdapter.fetchBettingMoney(playerName.value))
        }
    }

    fun announceInitialPlayersCards(blackJackGame: BlackjackGame) {
        val participantsResponse = ParticipantsResponse(blackJackGame.participants)
        outputView.printInitialCards(participantsResponse)
    }

    fun playGame(blackJackGame: BlackjackGame) {
        blackJackGame.getPlayers().forEach { player ->
            playTurnForPlayer(player, blackJackGame)
        }
        processDealerTurn(blackJackGame.getDealer(), blackJackGame)
    }

    private fun playTurnForPlayer(
        player: Player,
        blackJackGame: BlackjackGame,
    ) {
        while (shouldContinueDrawing(player, blackJackGame)) {
            outputView.printSinglePlayerCards(SinglePlayerResponse(player))
        }
        notifyPlayerCannotDraw(player)
    }

    private fun shouldContinueDrawing(
        player: Player,
        blackJackGame: BlackjackGame,
    ): Boolean {
        if (!player.isDrawable()) {
            return false
        }
        return isPlayerWantMore(player, blackJackGame)
    }

    private fun isPlayerWantMore(
        player: Player,
        blackJackGame: BlackjackGame,
    ): Boolean {
        val moreCardChoice = inputAdapter.fetchMoreCard(player.getName())
        return when (moreCardChoice) {
            HitStayChoice.HIT -> {
                player.addCard(blackJackGame.deck.drawCard())
                true
            }

            HitStayChoice.STAY -> {
                false
            }
        }
    }

    private fun notifyPlayerCannotDraw(player: Player) {
        if (!player.isDrawable()) {
            outputView.printPlayerCannotDrawCard(SinglePlayerResponse(player))
        }
    }

    private fun processDealerTurn(
        dealer: Dealer,
        blackJackGame: BlackjackGame,
    ) {
        while (dealer.isDrawable()) {
            dealer.addCard(blackJackGame.deck.drawCard())
            outputView.printDealerDrawAnnounceMessage()
        }
    }

    fun announceResult(blackJackGame: BlackjackGame) {
        val participantsResponse = ParticipantsResponse(blackJackGame.participants)
        outputView.printPlayResult(participantsResponse)

        val gameResult = blackJackGame.makeGameResult()
        outputView.printGameResult(GameResultResponse(gameResult))
    }
}
