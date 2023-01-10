package blackjack.controller

import blackjack.domain.BlackJackMachine
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    fun execute() {
        val playerNameList = InputView.readName().split(",")
        val players = playerNameList.map { Player(name = it) }.plus(Dealer("딜러"))
        val blackJackMachine = BlackJackMachine(participants = players)

        blackJackMachine.initialize()
        OutputView.printInitialCards(players)

        blackJackMachine.execute(retryOrNot(), playerCardResult(), dealerCardResultFunc())
        OutputView.printGameResult(players)
    }

    private fun retryOrNot(): (Player) -> Boolean = { player: Player -> InputView.hitOrNot(player.name) }

    private fun playerCardResult(): (Player) -> Unit = OutputView::printPlayerCards

    private fun dealerCardResultFunc(): (Dealer) -> Unit = OutputView::printDealerCardResult
}
