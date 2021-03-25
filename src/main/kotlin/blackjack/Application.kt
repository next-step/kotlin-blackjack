package blackjack

import blackjack.controller.BlackJackGame
import blackjack.domain.Players
import blackjack.view.InputView

fun main() {
    val playerNames = InputView.enterPlayerNames()
    val players = Players(playerNames)
    BlackJackGame.start(players)
}
