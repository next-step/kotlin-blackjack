package blackjack.controller

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun play() {
        val dealer = Dealer()
        val players = Players.of(playNameValues = inputView.inputPlayerNames(), dealer = dealer)
        outputView.printInitHands(players.values)

        drawCardsEachPlayers(players, dealer)
        outputView.printResult(players.values)
    }

    private fun drawCardsEachPlayers(players: Players, dealer: Dealer) {
        while (players.isNotAllFinished()) {
            drawCardsPlayer(players.getCurrentTurnPlayer(), dealer)
        }
    }

    private fun drawCardsPlayer(player: Player, dealer: Dealer) {
        while (player.isRunning()) {
            when (inputView.receiveOneMoreCard(player.getPlayerNameValue())) {
                true -> player.receiveCard(card = dealer.drawCard())
                false -> player.stay()
            }

            outputView.printPlayerHand(player = player)
        }
    }
}
