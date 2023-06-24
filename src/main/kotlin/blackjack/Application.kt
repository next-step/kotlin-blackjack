package blackjack

import blackjack.domain.Players
import blackjack.view.inputPlayerNames

fun main() {
    val players = Players.from(inputPlayerNames())
}
