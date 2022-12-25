package blackjack.controller

import blackjack.domain.PockerMachine
import blackjack.domain.person.Dealer
import blackjack.domain.person.Participant
import blackjack.domain.person.Player
import blackjack.domain.strategy.SequentialCardPickStrategy
import blackjack.util.Parser
import blackjack.view.InputView
import blackjack.view.OutputView

class PockerController {
    fun execute() {
        val dealer = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy())
        val nameList = Parser.parse(InputView.readName())
        val players = listOf(dealer) + nameList.map { name ->
            val money = InputView.readBettingMoney(name)
            Participant(name = name, money = money)
        }
        val pockerMachine = PockerMachine(dealer = dealer, players = players)

        pockerMachine.initialize()
        OutputView.printInitialState(players)

        pockerMachine.addCard(retryOrNot(), OutputView::printCardState)
        OutputView.printDealerPickOneMoreCard(dealer)
        OutputView.printResult(players)

        val gameResult = pockerMachine.getGameResult()
        OutputView.printGameResult(gameResult)

        val bettingMoneyResult = pockerMachine.getBettingResult()
        OutputView.printBettingMoneyResult(bettingMoneyResult)
    }

    private fun retryOrNot() = { player: Player -> InputView.retryOrNot(player.name) }
}
