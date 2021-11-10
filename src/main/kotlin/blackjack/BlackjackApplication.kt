package blackjack

import blackjack.domain.Players
import blackjack.view.InputView

fun main() {
    val playerNames: List<String> = InputView.inputPlayers()
    val players = Players.from(playerNames)
}
