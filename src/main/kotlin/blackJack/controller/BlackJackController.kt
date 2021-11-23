package blackJack.controller

import blackJack.domain.player.GamePlayers
import blackJack.domain.card.PlayingCard
import blackJack.domain.player.Dealer
import blackJack.domain.player.Player
import blackJack.domain.result.Results
import blackJack.dto.DealerDto
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
        startedPlayer.getPlayers().forEach {
            if (!it.isBlackJackPlayer()) {
                continuousPlayerReceiveCard(it, playingCard)
            }
        }
        continuousDealerReceiveCard(startedPlayer.getDealer(), playingCard)
    }

    private fun continuousPlayerReceiveCard(player: Player, playingCard: PlayingCard) {
        while (player.getAbleReceivedCard()) {
            val isContinue = inputView.doYouWantCardView(PlayerDto.of(player))
            player.receiveCard(isContinue) {
                playingCard.drawCard()
            }
            resultView.receiveCard(PlayerDto.of(player), isContinue)
        }
    }

    private fun continuousDealerReceiveCard(dealer: Dealer, playingCard: PlayingCard) {
        val isContinue = dealer.getAbleReceivedCard()
        dealer.receiveCard(isContinue) {
            playingCard.drawCard()
        }
        resultView.receiveCardToDealer(DealerDto.of(dealer), isContinue)
    }

    private fun resultingGame(inGamePlayers: GamePlayers) {
        inGamePlayers.getPlayers().forEach {
            resultView.playerGameResult(PlayerDto.of(it))
        }
        resultView.dealerGameResult(DealerDto.of(inGamePlayers.getDealer()))
    }
}
