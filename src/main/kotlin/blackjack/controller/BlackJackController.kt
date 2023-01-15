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
        val participants = playerNameList.map { Player(name = it) }.plus(dealer)
        val blackJackMachine = BlackJackMachine(participants = participants)

        blackJackMachine.initialize()
        OutputView.printInitialCards(participants)

        blackJackMachine.execute(retryOrNot(), playerCardResult(), dealerCardResultFunc())
        OutputView.printGameResult(participants)

        val playerResults = Judgment.execute(participants)
        OutputView.printPlayerResult(playerResults, dealer)
    }

    private fun retryOrNot(): (Player) -> Boolean = { player: Player -> InputView.hitOrNot(player.name) }

    private fun playerCardResult(): (Player) -> Unit = OutputView::printPlayerCards

    private fun dealerCardResultFunc(): (Dealer) -> Unit = OutputView::printDealerCardResult
}
