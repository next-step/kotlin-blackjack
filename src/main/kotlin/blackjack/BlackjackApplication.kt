package blackjack

import blackjack.domain.ConsolePlayerDecision
import blackjack.domain.Game
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playerNames = InputView.inputPlayerNames().split(",").map { it.trim() }
    val game = Game(playerNames)
    val decisionMaker = ConsolePlayerDecision()

    val (dealerCard, playerCards) = game.getInitialState()
    ResultView.printInitialCards(dealerCard, playerCards)

    val (results, dealerDrewCard) = game.start(decisionMaker)

    ResultView.printDealerDrawMessage(dealerDrewCard)

    val (playerScores, dealerState) = game.getFinalScores()
    ResultView.printFinalScores(dealerState, playerScores)

    ResultView.printGameResult(results)
}

