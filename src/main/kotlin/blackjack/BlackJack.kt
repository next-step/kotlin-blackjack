package blackjack

import blackjack.domain.game.Game
import blackjack.view.InputView.inputPlayers
import blackjack.view.ViewResolver

fun main() {
    val game = Game(
        players = inputPlayers(),
        viewResolver = ViewResolver()
    )
    game.start()
}
