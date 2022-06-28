package camp.nextstep.blackjack

import camp.nextstep.blackjack.ui.cli.BlackJackController

fun main() {
    val game = BlackJackController.newGame()
    BlackJackController.playGame(game)
    BlackJackController.closeGame(game)
}
