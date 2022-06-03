package blackjack.game

import blackjack.util.inputPlayers

fun main() {
    val game = Game(inputPlayers())
    game.start()
}
