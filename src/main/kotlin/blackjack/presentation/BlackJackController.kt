package blackjack.presentation

import blackjack.core.BlackJackService
import blackjack.core.player.Dealer
import blackjack.core.player.Players

object BlackJackController {
    fun start() {
        val names = InputView.getNames()
        val bettingAmounts = InputView.getBettingAmounts(names)

        val players = Players(names, bettingAmounts)
        val dealer = Dealer()

        BlackJackService.start(dealer, players)
        ResultView.printResult(dealer, players)
        ResultView.printProfits(dealer, players)
    }
}
