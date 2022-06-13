package blackjack

import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val names: List<String> = InputView().getPlayers()
    val players: Players = Players.of(names)

    ResultView().printStartCard(players)
}
