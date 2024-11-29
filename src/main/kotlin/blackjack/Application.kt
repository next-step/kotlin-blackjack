package blackjack

import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView


fun main() {

    val inputView = InputView()
    val resultView = ResultView()
    val players = inputView.getPlayers().map {
        Player(it)
    }

    val game = Game(players)
    game.startGame()

    game.getResult()


    resultView.printGameResult()
}
