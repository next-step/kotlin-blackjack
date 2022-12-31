package blackjack

import blackjack.io.Input
import blackjack.io.Output

fun main() {
    val input = Input()
    val output = Output()
    val game = Game(input, output)

    game.start()
    game.draw()
    game.result()
}
