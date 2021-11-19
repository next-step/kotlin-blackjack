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
        resultingGame(gamePlayers)
        resultView.winOrLoseView(Results.from(gamePlayers))
    }

    private fun playingGame(startedPlayer: GamePlayers, playingCard: PlayingCard) {
        startedPlayer.forEach {
            if (!it.isBlackJackPlayer() && it.isPlayer()) {
                continuousPlayerReceiveCard(it, playingCard)
            } else {
                continuousDealerReceiveCard(it, playingCard)
            }
        }
    }

    private fun continuousPlayerReceiveCard(player: GamePlayer, playingCard: PlayingCard) {
        while (player.getAbleReceivedCard()) {
            val isContinue = inputView.doYouWantCardView(PlayerDto.of(player))
            player.receiveCard(isContinue) {
                playingCard.drawCard()
            }
            resultView.receiveCard(PlayerDto.of(player), isContinue)
        }
    }

    private fun continuousDealerReceiveCard(player: GamePlayer, playingCard: PlayingCard) {
        val isContinue = player.getAbleReceivedCard()
        player.receiveCard(isContinue) {
            playingCard.drawCard()
        }
        resultView.receiveCardToDealer(PlayerDto.of(player), isContinue)
    }

    private fun resultingGame(inGamePlayers: GamePlayers) {
        inGamePlayers.forEach {
            resultView.gameResult(PlayerDto.of(it))
        }
    }
}
