package blackjack.controller

import blackjack.domain.game.BlackJack
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val game = BlackJack(InputView.getNames())
    game.players.map { it.bet(InputView.getBettingAmount(it)) }

    game.distributeInitialCard()
    ResultView.printFirstCards(game)

    while (!game.isEnd()) {
        val player = game.getNowPlayer()
        val answer = InputView.getAnswer(player)
        game.playGameTurn(answer)
        ResultView.printPlayerCards(player)
    }

    if (game.dealer.continueDrawing) {
        game.distributeCardForDealer()
        ResultView.printAddDealerCard()
    }

    ResultView.printScore(game)
    ResultView.printResult(game, game.getResult())
}
