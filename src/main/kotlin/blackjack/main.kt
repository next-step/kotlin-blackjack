package blackjack

import blackjack.controller.GameController
import blackjack.domain.model.Game

fun main() {
    GameController().execute(Game())
}
