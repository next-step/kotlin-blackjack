package blackJack.controller

import blackJack.domain.GamePlayer
import blackJack.domain.GamePlayers
import blackJack.domain.PlayingCard
import blackJack.domain.Results
import blackJack.dto.PlayerDto
import blackJack.dto.GamePlayersDto
import blackJack.view.InputView
import blackJack.view.ResultView

class BlackJackController(private val inputView: InputView, private val resultView: ResultView) {

    fun start() {
        val playingCard = PlayingCard.create()
        val inputPlayersNames = inputView.inputPlayersName()
        val gamePlayers = GamePlayers.enterGameRoom(inputPlayersNames)
        gamePlayers.startBlackJack(playingCard)
        resultView.receiveTwoCard(GamePlayersDto.of(gamePlayers))
        playingGame(gamePlayers, playingCard)
        val gameOverPlayers = resultingGame(gamePlayers)
        resultView.winOrLoseView(Results.from(gameOverPlayers))
    }

    private fun playingGame(startedPlayer: GamePlayers, playingCard: PlayingCard) {
        startedPlayer.toList().forEach {
            if (!it.isBlackJackPlayer() && !it.isDealer()) {
                continuousPlayerReceiveCard(it, playingCard)
            } else {
                continuousDealerReceiveCard(it, playingCard)
            }
        }
    }

    private fun continuousDealerReceiveCard(player: GamePlayer, playingCard: PlayingCard) {
        if (player.getAbleReceivedCard()) {
            player.receiveCard(playingCard.drawCard())
            resultView.receiveCardToDealer(PlayerDto.of(player))
        } else {
            resultView.noReceiveCardToDealer(PlayerDto.of(player))
        }
    }

    private fun continuousPlayerReceiveCard(player: GamePlayer, playingCard: PlayingCard) {
        while (player.getAbleReceivedCard()) {
            val isContinue = inputView.doYouWantCardView(PlayerDto.of(player))
            updatePlayerStatus(isContinue, player, playingCard)
        }
    }

    private fun updatePlayerStatus(isContinue: Boolean, player: GamePlayer, playingCard: PlayingCard) {
        if (isContinue) {
            player.receiveCard(playingCard.drawCard())
            resultView.receiveCard(PlayerDto.of(player))
        } else {
            player.noReceiveCard()
        }
    }

    private fun resultingGame(inGamePlayers: GamePlayers): GamePlayers =
        GamePlayers(inGamePlayers.toList().map {
            resultView.gameResult(PlayerDto.of(it))
            return@map it
        })
}
