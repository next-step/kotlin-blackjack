package blackJack.controller

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
            receiveCard(it, playingCard)
            PlayerDto.of(it)
        }.forEach {
            resultView.gameResult(it)
        }
    }

    private fun receiveCard(player: Player, playingCard: PlayingCard) {
        if (!player.isBlackJackPlayer()) {
            continuousReceiveCard(player, playingCard)
        }
    }

    private fun continuousReceiveCard(player: Player, playingCard: PlayingCard) {
        while (player.getAbleReceivedCard()) {
            val isContinue = inputView.doYouWantCardView(PlayerDto.of(player))
            updatePlayerStatus(isContinue, player, playingCard)
        }
    }

    private fun updatePlayerStatus(isContinue: Boolean, player: Player, playingCard: PlayingCard) {
        if (isContinue) {
            player.receiveCard(playingCard.drawCard())
            resultView.receiveCard(PlayerDto.of(player))
        } else {
            player.noReceiveCard()
        }
    }
}
