package blackjack

import blackjack.controller.GameController
import blackjack.domain.model.Round

fun main() {
    GameController().execute(Round())
}
