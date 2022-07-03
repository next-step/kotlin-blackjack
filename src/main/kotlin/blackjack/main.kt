package blackjack

import blackjack.application.BlackJackService
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Status
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.response.PlayerResponse

fun main() {
    val deck = Deck()
    val players = BlackJackService.createPlayers(InputView.inputPlayers())
    val dealer = Dealer()
    BlackJackService.drawFirst(dealer, players, deck)

    OutputView.printPlayer(PlayerResponse.from(dealer))
    OutputView.printPlayers(players.map { PlayerResponse.from(it) })

    for (player in players) {
        while (player.status != Status.BUST) {
            if (player.status == Status.STAY) break

            val willDraw = InputView.inputWillDraw(player.name)
            if (!willDraw) {
                break
            }


            BlackJackService.draw(player, deck, 1)
            OutputView.printPlayer(PlayerResponse.from(player))
        }
    }

    if (dealer.status == Status.HIT) {
        BlackJackService.draw(dealer, deck, 1)
        OutputView.printDealerDraw()
    }

    OutputView.printOutput(BlackJackService.calculate(dealer, players))
}
