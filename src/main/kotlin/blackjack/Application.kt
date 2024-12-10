package blackjack

import blackjack.CardGame.Companion.INITIAL_CARD_COUNT
import blackjack.domain.Dealer.Companion.DEALER_HIT_SCORE
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView
import blackjack.ui.UserMore
import blackjack.ui.UserName

private val inputView = InputView()
private val resultView = ResultView()

fun main() {
    val game = CardGame.create(inputView.inputUserNames())
    val resultEvaluator = game.resultEvaluator()

    game.startGame()
    game.handlePlayerAmount(::amountOfPlayers)

    resultView.printUserCardCount(game.allPlayersNames(), INITIAL_CARD_COUNT)
    resultView.printUserCards(resultEvaluator.evaluateRounds())

    game.handleUserTurn(::playerTurn, resultView::printRound)
    game.handleDealerTurn(::dealerTurn)

    resultView.printScoreResult(resultEvaluator.evaluateRounds())
    resultView.printFinalProfit(resultEvaluator.finalMatchEvaluate())
}

private fun amountOfPlayers(userName: UserName): Int {
    return inputView.inputPlayerAmount(userName)
}

private fun playerTurn(player: Player): UserMore {
    val playerName = player.name
    return inputView.inputMore(playerName)
}

private fun dealerTurn() {
    resultView.printDealerTurnStart(DEALER_HIT_SCORE.value)
}
