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
        game.playGameTurn(answer)
        printCards(player, answer)
    }

    ResultView.printResult(game)
}

fun printCards(player: Player, isPlaying: Boolean) {
    if (isPlaying) {
        ResultView.printCards(player)
    }
}
