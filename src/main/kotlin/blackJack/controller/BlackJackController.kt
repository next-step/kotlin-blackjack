package blackJack.controller

import blackJack.domain.BlackJack
import blackJack.domain.Player
import blackJack.domain.Players
import blackJack.domain.PlayingCard
import blackJack.dto.PlayerDto
import blackJack.dto.PlayersDto
import blackJack.view.InputView
import blackJack.view.ResultView

class BlackJackController(private val inputView: InputView, private val resultView: ResultView) {

    fun start() {
        val playingCard = PlayingCard.create()
        val inputPlayersNames = inputView.inputPlayersName()
        val players = Players.enterGameRoom(inputPlayersNames)
        val startedPlayer = players.startBlackJack(playingCard)
        resultView.receiveTwoCard(PlayersDto.of(startedPlayer))

        startedPlayer.toList().map {
            val player = receiveCard(it, playingCard)
            PlayerDto.of(player)
        }.forEach {
            resultView.gameResult(it)
        }
    }

    private fun receiveCard(player: Player, playingCard: PlayingCard): Player {
        val playerStatus = player.getPlayerDecisionStatus()
        if (playerStatus !is BlackJack) {
            return continuousReceiveCard(player, playingCard)
        }
        return player
    }

    private fun continuousReceiveCard(player: Player, playingCard: PlayingCard): Player {
        while (player.getAbleReceivedCard()) {
            val isContinue = inputView.doYouWantCardView(PlayerDto.of(player))
            return updatePlayerStatus(isContinue, player, playingCard)
        }
        return player
    }

    private fun updatePlayerStatus(isContinue: Boolean, player: Player, playingCard: PlayingCard): Player {
        if (isContinue) {
            player.receiveCard(playingCard.drawCard())
            resultView.receiveCard(PlayerDto.of(player))
        } else {
            player.noReceiveCard()
        }
        return player
    }
}
