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
        var _player = player
        val playerStatus = _player.status.decisionStatus
        if (playerStatus !is BlackJack) {
            _player = continuousReceiveCard(_player, playingCard)
        }
        return _player
    }

    private fun continuousReceiveCard(player: Player, playingCard: PlayingCard): Player {
        var _player = player
        while (_player.status.ableGetACard()) {
            val isContinue = inputView.doYouWantCardView(PlayerDto.of(_player))
            _player = updatePlayerStatus(isContinue, _player, playingCard)
        }
        return _player
    }

    private fun updatePlayerStatus(isContinue: Boolean, player: Player, playingCard: PlayingCard): Player {
        var _player = player
        if (isContinue) {
            _player = _player.receiveCard(playingCard.drawCard())
            resultView.receiveCard(PlayerDto.of(_player))
        } else {
            _player = _player.noReceiveCard()
        }
        return _player
    }
}
