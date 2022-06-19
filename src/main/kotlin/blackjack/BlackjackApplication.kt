package blackjack

import blackjack.domain.PlayGame
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val names = inputView.getNames()
    val players = names.map(::Player)

    val playGame = PlayGame(players)
    playGame.start()

    outputView.firstCard(players)
}
