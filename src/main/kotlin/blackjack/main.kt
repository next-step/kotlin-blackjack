package blackjack

import blackjack.application.BlackJackService
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val players = BlackJackService.createPlayers(InputView.inputPlayers())
    players.forEach { OutputView.printPlayer(it) }

    for (player in players) {
        while (BlackJackService.isBurst(player)) {
            val willDraw = InputView.inputWillDraw(player.name)
            if (!willDraw) {
                break
            }
            BlackJackService.draw(player, 1)
            OutputView.printPlayer(player)
        }
    }

    OutputView.printOutput(BlackJackService.calculate(players))
}
