package blackjack

import blackjack.CardGame.Companion.INITIAL_CARD_COUNT
import blackjack.domain.Dealer.Companion.DEALER_HIT_SCORE
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView
import blackjack.ui.UserCards
import blackjack.ui.UserMore

private val inputView = InputView()
private val resultView = ResultView()

fun main() {
    val game = CardGame.create(inputView.inputUserNames())
    val resultEvaluator = game.resultEvaluator()

    game.startGame()

    resultView.printUserCardCount(game.dealerName, game.allPlayersNames(), INITIAL_CARD_COUNT)
    resultView.printUserCards(resultEvaluator.evaluateRounds())

    game.handleUserTurn(::playerTurn)
    game.handleDealerTurn(::dealerTurn)

    resultView.printScoreResult(resultEvaluator.evaluateRounds())
    resultView.printFinalWinner(resultEvaluator.finalMatchEvaluate())
}

private fun playerTurn(
    player: Player,
    userHandCards: UserCards,
): UserMore {
    val playerName = player.name
    resultView.printRound(playerName, userHandCards)
    return inputView.inputMore(playerName)
}

private fun dealerTurn() {
    resultView.printDealerTurnStart(DEALER_HIT_SCORE.value)
}
