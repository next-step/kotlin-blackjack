package blackjack.controller

import blackjack.domain.dealer.Dealer
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    val inputView: InputView = InputView(),
    val ouputView: OutputView = OutputView(),
) {
    fun play() {
        val dealer = Dealer()
        val players = Players.of(playNameValues = inputView.inputPlayerNames())
        players.receiveInitCards(dealer)
        ouputView.printInitHands(players.values)
    }
}
