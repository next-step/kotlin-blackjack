package blackjack

import blackjack.domain.Players
import blackjack.view.DisplayView
import blackjack.view.InputView

class BlackJackGame {

    fun start() {
        val names = InputView.inputNameOfPlayer()
        val players = Players(names)
        DisplayView.dealOutCards(players)
    }
}

fun main() {
    val game = BlackJackGame()
    game.start()
}
