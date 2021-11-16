package blackJack.controller

import blackJack.domain.GamePlayer
import blackJack.domain.GamePlayers
import blackJack.domain.PlayingCard
import blackJack.dto.PlayerDto
import blackJack.dto.GamePlayersDto
import blackJack.view.InputView
import blackJack.view.ResultView

class BlackJackController(private val inputView: InputView, private val resultView: ResultView) {

    fun start() {
        val playingCard = PlayingCard.create()
        val inputPlayersNames = inputView.inputPlayersName()
        val gamePlayers = GamePlayers.enterGameRoom(inputPlayersNames)
        val startedPlayer = gamePlayers.startBlackJack(playingCard)
        resultView.receiveTwoCard(GamePlayersDto.of(startedPlayer))

        startedPlayer.toList().map {
            receiveCard(it, playingCard)
            PlayerDto.of(it)
        }.forEach {
            resultView.gameResult(it)
        }
    }

    private fun receiveCard(player: GamePlayer, playingCard: PlayingCard) {
        if (!player.isBlackJackPlayer() && !player.isDealer()) {
            continuousPlayerReceiveCard(player, playingCard)
        } else {
            continuousDealerReceiveCard(player, playingCard)
        }
    }

    private fun continuousDealerReceiveCard(player: GamePlayer, playingCard: PlayingCard) {
        if(player.getAbleReceivedCard()) {
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
}
