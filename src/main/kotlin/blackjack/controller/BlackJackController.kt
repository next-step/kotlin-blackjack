package blackjack.controller

import blackjack.domain.BlackJackMachine
import blackjack.domain.Dealer
import blackjack.domain.Judgment
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    fun execute() {
        val playerNameList = InputView.readName().split(",")
        val dealer = Dealer("딜러")
        val players = playerNameList.map { Player(name = it) }
        val blackJackMachine = BlackJackMachine(players = players, dealer = dealer)

        blackJackMachine.initialize()
        OutputView.printInitialCards(players.plus(dealer))

        blackJackMachine.executePlayer(retryOrNot(), playerCardResult())
        blackJackMachine.executeDealer(dealerCardResultFunc())
        OutputView.printGameResult(players.plus(dealer))

        val playerResults = Judgment.execute(players, dealer)
        OutputView.printPlayerResult(playerResults, dealer)
    }

    private fun retryOrNot(): (Player) -> Boolean = { player: Player -> InputView.hitOrNot(player.name) }

    private fun playerCardResult(): (Player) -> Unit = OutputView::printPlayerCards

    private fun dealerCardResultFunc(): (Dealer) -> Unit = OutputView::printDealerCardResult
}
