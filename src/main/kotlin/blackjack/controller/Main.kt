package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val game = BlackJack(InputView.getNames())
    game.distributeInitialCard()
    ResultView.printFirstCards(game)

    while (!game.isEnd()) {
        val player = game.getNowPlayer()
        val answer = InputView.getAnswer(player)
        game.playGameTurn(answer)
        ResultView.printPlayerCards(player)
    }

    if (game.askDealerForAdditionalCard()) {
        ResultView.printAddDealerCard()
    }

    ResultView.printScore(game)
    ResultView.printResult(game, game.getResult())
}
