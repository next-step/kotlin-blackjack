package blackjack.view

import blackjack.domain.game.Game
import blackjack.util.inputPlayers

fun main() {
    val game = Game(inputPlayers())
    game.start()
}
