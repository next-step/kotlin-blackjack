package blackjack

import blackjack.config.GameConfig

fun main() {
    val controller = GameConfig.gameController

    controller.play()
}
