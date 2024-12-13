package blackjack

import blackjack.domain.ConsolePlayerDecision
import blackjack.domain.Game
import blackjack.domain.PlayerInfo
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playersInfo =
        InputView.inputPlayerNames()
            .split(",")
            .map { it.trim() }
            .map { name ->
                val bet = InputView.inputPlayerBet(name)
                PlayerInfo(name, bet)
            }
    val game = Game(playersInfo)
    val decisionMaker = ConsolePlayerDecision()

    val (dealerCard, playerCards) = game.getInitialState()
    ResultView.printInitialCards(dealerCard, playerCards)

    val (results, dealerDrewCard) = game.start(decisionMaker)

    ResultView.printDealerDrawMessage(dealerDrewCard)

    val (playerScores, dealerState) = game.getFinalScores()
    ResultView.printFinalScores(dealerState, playerScores)

    val profits = game.calculateProfits()
    ResultView.printProfits(profits)
}
