package blackjack

import blackjack.CardGame.Companion.INITIAL_CARD_COUNT
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val names = inputView.inputUserNames()
    val game = CardGame.fromNames(names)

    game.playerAllDeal()
    resultView.printUserCardCount(names, INITIAL_CARD_COUNT)
    resultView.printUserCards(game.roundResult())

    names.forEach { userName -> handleUserTurn(game, resultView, inputView, userName) }

    val resultDto = game.result()
    resultView.printResult(resultDto)
}

private fun handleUserTurn(
    game: CardGame,
    resultView: ResultView,
    inputView: InputView,
    userName: String,
) {
    while (inputView.inputMore(userName)) {
        game.deal(userName)
        val userHandCards = game.userCardOf(userName)
        resultView.printRound(userName, userHandCards)

        if (game.isBust(userName)) {
            break
        }
    }
}
