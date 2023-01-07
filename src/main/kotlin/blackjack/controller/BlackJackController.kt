package blackjack.controller

import blackjack.domain.BlackJackMachine
import blackjack.domain.Participant
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    fun execute() {
        val playerNameList = InputView.readName().split(",")
        val players = playerNameList.map { Participant(name = it) }
        val blackJackMachine = BlackJackMachine(players = players)

        blackJackMachine.initialize()
        OutputView.printInitialCards(players)

        blackJackMachine.execute(retryOrNot(), playerCardResult())
        OutputView.printGameResult(players)
    }

    private fun retryOrNot(): (Participant) -> Boolean = { player: Participant -> InputView.hitOrNot(player.name) }

    private fun playerCardResult(): (Participant) -> Unit = OutputView::printPlayerCards
}
