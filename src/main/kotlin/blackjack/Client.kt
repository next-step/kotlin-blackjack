package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val dealer = Dealer()
    val players = InputView.inputPlayers()
    val blackJackGame = BlackjackGame(dealer, players)

    OutputView.printInitGame(blackJackGame)
    while (blackJackGame.isNotFinished()) {
        blackJackGame.players.forEach { player ->
            if (player.canDraw) {
                val inputDrawResponse =  InputView.inputDrawResponse(player)
                dealer.deal(player)
            }
        }
    }
}
