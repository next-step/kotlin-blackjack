package blackjack

import blackjack.model.BlackJackGame
import blackjack.view.InputView

fun main() {
    val players = InputView.getPlayer()
    val blackJackGame = BlackJackGame(players)

    blackJackGame.firstTurn()
}
