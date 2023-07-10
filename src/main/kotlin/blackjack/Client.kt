package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.PlayerState
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val dealer = Dealer()
    val players = InputView.inputPlayers()
    val blackJackGame = BlackjackGame(dealer, players)

    OutputView.printInitGame(blackJackGame)
    while (blackJackGame.isNotFinished()) {
        blackJackGame.players.forEach { player ->
            if (player.state == PlayerState.PLAYING) {
                val inputDrawResponse = InputView.inputDrawResponse(player)
                if (inputDrawResponse) {
                    dealer.deal(player)
                    OutputView.printCardsInHand(player)
                }
                else player.stopDraw()
            }
        }
    }
}
