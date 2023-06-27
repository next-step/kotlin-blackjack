package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val game = BlackJack(InputView.getNames())
    game.start()
    ResultView.printFirstCards(game)

    while (!game.isEnd()) {
        val player = game.getNowPlayer()
        val answer = InputView.getAnswer(player)
        game.playGameTurn(answer)
        ResultView.printCards(player)
    }

    if (game.askDealerForAdditionalCard()) {
        ResultView.printAddDealerCard()
    }

    val result = game.getResult()
    ResultView.printResult(game)
}
