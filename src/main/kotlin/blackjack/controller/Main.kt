package blackjack.controller

import blackjack.model.Player
import blackjack.model.PlayersFactory
import blackjack.model.Rule
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.ViewUtil

fun main() {
    val players = PlayersFactory.create(InputView.readNames())

    OutputView.printFirstDraw(players)

    players.forEach {
        drawUntilUserStop(it)
    }

    players.forEach {
        OutputView.printResult(it.name, it.cards, Rule.getScore(it.cards))
    }
}

private fun drawUntilUserStop(player: Player) {
    while (player.keepDrawing(InputView.readUserResponse(player.name))) {
        println("${player.name}카드: ${ViewUtil.toString(player.cards)}")
    }
}
