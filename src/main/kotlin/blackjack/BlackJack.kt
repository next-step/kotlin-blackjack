package blackjack

import blackjack.view.InputView.inputPlayers
import blackjack.view.ViewResolver

fun main() {
    val gameController = GameController(
        players = inputPlayers(),
        viewResolver = ViewResolver()
    )
    gameController.start()
}
