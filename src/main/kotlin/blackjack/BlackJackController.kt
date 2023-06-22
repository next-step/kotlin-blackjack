package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.OutputView.handNotice

object BlackJackController {
    fun startRound(players: Array<Player>) {
        val dealer = Dealer()
        dealer.startRound(players)
        OutputView.roundStartNotice(players)
        players.forEach { player ->
            while (player.ableToDraw) {
                val wantToHit = InputView.wantToHit(player.name)
                player.performAction(wantToHit, dealer)
                if (wantToHit) handNotice(player)
            }
        }
    }
}
