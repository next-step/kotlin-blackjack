package blackjack

import blackjack.controller.GameController
import blackjack.domain.model.Trump

fun main() {
    val trump = Trump()
    GameController().execute(trump)
}
