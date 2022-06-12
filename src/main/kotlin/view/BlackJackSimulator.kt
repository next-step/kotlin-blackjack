package domain

import domain.BlackJackGame.endCheck
import domain.BlackJackGame.setInitialCards
import view.InputView
import view.ResultView

fun main() {
    val players = InputView.getUserName()
    InputView.displayCardDivide(players.joinToString { it.name }, "2")

    setInitialCards(players)
    players.forEach {
        InputView.displayHaveCard(it)
    }

    do {
        var noCnt = 0
        var continueGame = false

        players.forEach {
            val enableToIssue = InputView.isYesOrNo(it.name)
            if (!enableToIssue) noCnt++

            val exceed21 = BlackJackGame.checkBustCondition(it, enableToIssue)

            InputView.displayHaveCard(it)

            continueGame = endCheck(noCnt, players.size, exceed21)
        }
    } while (continueGame)

    ResultView.displayResult(players)
}
