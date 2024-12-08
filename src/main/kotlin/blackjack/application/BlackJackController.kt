package blackjack.application

import blackjack.core.player.Dealer
import blackjack.core.player.Players
import blackjack.presentation.InputView
import blackjack.presentation.ResultView

object BlackJackController {
    fun start() {
        val names = InputView.getNames()

        val players = Players(names)
        val dealer = Dealer()
        BlackJackService.start(dealer, players)

        ResultView.printResult(dealer, players)
    }
}
