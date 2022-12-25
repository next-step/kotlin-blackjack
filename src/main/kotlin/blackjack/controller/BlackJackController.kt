package blackjack.controller

import blackjack.domain.BlackJackMachine
import blackjack.domain.person.Dealer
import blackjack.domain.person.Participant
import blackjack.domain.person.Player
import blackjack.domain.strategy.SequentialCardPickStrategy
import blackjack.util.Parser
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    fun execute() {
        val dealer = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy())
        val nameList = Parser.parse(InputView.readName())
        val players = listOf(dealer) + nameList.map { name ->
            val money = InputView.readBettingMoney(name)
            Participant(name = name, money = money)
        }
        val blackJackMachine = BlackJackMachine(dealer = dealer, players = players)

        blackJackMachine.initialize()
        OutputView.printInitialState(players)

        blackJackMachine.addCard(retryOrNot(), OutputView::printCardState)
        OutputView.printDealerPickOneMoreCard(dealer)
        OutputView.printResult(players)

        val gameResult = blackJackMachine.getGameResult()
        OutputView.printGameResult(gameResult)

        val bettingMoneyResult = blackJackMachine.getBettingResult()
        OutputView.printBettingMoneyResult(bettingMoneyResult)
    }

    private fun retryOrNot() = { player: Player -> InputView.retryOrNot(player.name) }
}
