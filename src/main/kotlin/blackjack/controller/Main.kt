package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val game = BlackJack(InputView.getNames())
    game.start()
    ResultView.printFirstCards(game)

    while (!game.isEnd()) {
        val player = game.getNowPlayer()
        val answer = InputView.getAnswer(player)
        val count = game.playGameTurn(answer)
        printCards(player, answer, count)
    }

    ResultView.printResult(game)
}

fun printCards(player: Player, answer: String, count: Int) {
    if (answer == "n" && count != 0) {
        return
    }
    ResultView.printCards(player)
}
