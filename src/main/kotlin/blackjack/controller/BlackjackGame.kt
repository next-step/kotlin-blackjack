package blackjack.controller

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    val inputView: InputView = InputView(),
    val ouputView: OutputView = OutputView(),
) {
    fun play() {
        val dealer = Dealer()
        val players = Players.enrollPlayers(playNameValues = inputView.inputPlayerNames())
        players.receiveInitCards(dealer)
        ouputView.printInitHands(players.values)
    }
}
