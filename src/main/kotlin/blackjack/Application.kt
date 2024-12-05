package blackjack

import blackjack.CardGame.Companion.INITIAL_CARD_COUNT
import blackjack.domain.Dealer.Companion.DEALER_HIT_SCORE
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val names = inputView.inputUserNames()
    val game = CardGame.create(names)
    val resultEvaluator = game.resultEvaluator()

    game.startGame()
    resultView.printUserCardCount(game.dealerName, game.allPlayersNames(), INITIAL_CARD_COUNT)
    resultView.printUserCards(resultEvaluator.evaluateRounds())

    names.forEach { userName -> handleUserTurn(game, resultView, inputView, userName) }

    handleDealerTurn(game, resultView)

    resultView.printScoreResult(resultEvaluator.evaluateRounds())
    resultView.printFinalWinner(resultEvaluator.winMatchEvaluate())
}

private fun handleUserTurn(
    game: CardGame,
    resultView: ResultView,
    inputView: InputView,
    userName: String,
) {
    while (inputView.inputMore(userName)) {
        game.dealCardToPlayer(userName)
        val userHandCards = game.playerCards(userName)
        resultView.printRound(userName, userHandCards)

        if (game.isPlayerBust(userName)) {
            break
        }
    }
}

private fun handleDealerTurn(
    game: CardGame,
    resultView: ResultView,
) {
    if (game.dealerShouldAddCard) {
        resultView.printDealerTurnStart(DEALER_HIT_SCORE.value)
        game.dealCardToDealer()
    }
}
