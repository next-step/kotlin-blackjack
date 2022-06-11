package domain

import domain.BlackJackGame.endCheck
import domain.BlackJackGame.setInitialCards
import view.InputView
import view.ResultView

fun main() {
    val playerNames = InputView.getUserName()
    InputView.displayCardDivide(playerNames.joinToString { it.name }, "2")

    val players = setInitialCards(playerNames)
    players.forEach {
        InputView.displayHaveCard(it)
        println()
    }
    println()

    do {
        var noCnt = 0
        var continueGame = false

        players.forEach {
            val enableToIssue = InputView.isYesOrNo(it.name)
            if (!enableToIssue) noCnt++

            val exceed21 = BlackJackGame.issueAndCheck(it, enableToIssue)

            InputView.displayHaveCard(it)
            println()
            println()

            continueGame = endCheck(noCnt, players.size, exceed21)
        }
    } while (continueGame)

    ResultView.displayResult(players)
}
