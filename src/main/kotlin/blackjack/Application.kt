package blackjack

import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val names = inputView.inputUserNames()
    val game = CardGame.fromOf(names)

    game.playerAllHand()
    resultView.printUserCardCount(names, game.playersSize)
    resultView.printUserCardsOfMap(names.associateWith { game.cardsOf(it) })

    names.forEach { userName -> handleUserTurn(game, resultView, inputView, userName) }

    val resultDto = game.result()
    displayGameResult(resultView, resultDto)
}

private fun handleUserTurn(
    game: CardGame,
    resultView: ResultView,
    inputView: InputView,
    userName: String,
) {
    while (inputView.inputMore(userName)) {
        game.hand(userName)
        val userHandCards = game.cardsOf(userName)
        val score = game.scoreOf(userName)

        resultView.printUserCards(userName, userHandCards)
        resultView.printResult(userName, userHandCards, score)

        if (game.isBust(userName)) {
            break
        }
    }
}

private fun displayGameResult(
    resultView: ResultView,
    resultDto: Map<String, Map<List<String>, Int>>,
) {
    resultView.outputProvider("")
    resultDto.forEach { (userName, cardAndScoreMap) ->
        cardAndScoreMap.forEach { (cards, score) ->
            resultView.printResult(userName, cards, score)
        }
    }
}
