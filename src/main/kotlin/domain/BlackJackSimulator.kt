package domain

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

        players.forEach {
            val enableToIssue = InputView.isYesOrNo(it.name)

            if (enableToIssue) {
                val cards = BlackJackGame.issue()
                it.offer(cards)
            }
            InputView.displayHaveCard(it)
            println()
            println()

            if (!enableToIssue) noCnt++
        }
    } while (noCnt != players.size)

    ResultView.displayResult(players)
}
