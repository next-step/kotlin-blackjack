package blackjack

import blackjack.application.BlackJackService
import blackjack.domain.Deck
import blackjack.domain.Status
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.response.PlayerResponse

fun main() {
    val deck = Deck()
    val players = BlackJackService.createPlayers(InputView.inputPlayers())
    BlackJackService.drawFirst(players, deck)

    OutputView.printPlayers(players.map { PlayerResponse.from(it) })

    for (player in players) {
        while (player.status != Status.BUST) {
            val willDraw = InputView.inputWillDraw(player.name)
            if (!willDraw) {
                break
            }
            BlackJackService.draw(player, deck, 1)
            OutputView.printPlayer(PlayerResponse.from(player))
        }
    }

    OutputView.printOutput(BlackJackService.calculate(players))
}
