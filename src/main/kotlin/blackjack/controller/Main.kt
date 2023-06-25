package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.view.InputView

fun main() {
    val players = InputView.getNames()
    val game = BlackJack(players)
    game.start()
}