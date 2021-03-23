package blackjack.controller

import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.ViewUtil

fun main() {
    val players = Players.Builder().playerNames(InputView.readNames()).build()

    OutputView.printFirstDraw(players)

    players.forEach {
        drawUntilUserStop(it)
    }

    OutputView.printResult(players)
}

private fun drawUntilUserStop(player: Player) {
    while (true) {
        if (InputView.readUserResponse(player.name) != "y") {
            break
        }
        player.draw()
        println("${player.name}카드: ${ViewUtil.toString(player.cards)}")
    }
}
