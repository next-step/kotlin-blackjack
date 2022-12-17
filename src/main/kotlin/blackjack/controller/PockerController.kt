package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.PockerMachine
import blackjack.domain.strategy.SequentialCardPickStrategy
import blackjack.util.Parser
import blackjack.view.InputView
import blackjack.view.OutputView

class PockerController {
    fun execute() {
        val dealer = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy())
        val nameList = Parser.parse(InputView.readName())
        val players = listOf(dealer) + nameList.map { name -> Participant(name = name) }
        val pockerMachine = PockerMachine(dealer = dealer, players = players)

        pockerMachine.initialize()
        OutputView.printInitialState(players)

        pockerMachine.addCard(retryOrNot(), OutputView::printCardState)
        OutputView.printDealerPickOneMoreCard(dealer)
        OutputView.printResult(players)
    }

    private fun retryOrNot() = { player: Player -> InputView.retryOrNot(player.name) }
}
