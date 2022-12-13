package blackjack

import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val inputNames = inputView.playersName()
    val names = inputNames.split(",")
    val players = names.map { Player(it.trim()) }

    val cardProvider = CardProvider(players, CardRepository())
    cardProvider.start()
    resultView.startTakeCardPlayers(players)

    players.forEach { player ->
        do {
            val allow = inputView.takeCardAllow(player.name)
            val hasNext = cardProvider.hasAllowTakeCard(player, allow)
            resultView.takeCardPlayer(player)
        } while (hasNext)
    }

    resultView.playersResult(players)
}
