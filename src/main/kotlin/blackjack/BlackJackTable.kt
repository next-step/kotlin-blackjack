package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackTable(
    private val players: Array<Player>,
    private val dealer: Dealer = Dealer()
) {
    fun startRound() {
        dealer.startRound(players)
        OutputView.roundStartNotice(players)
    }

    fun playEachTurn(player: Player) {
        while (player.ableToDraw) {
            proceedTurn(player)
        }
    }

    private fun proceedTurn(player: Player) {
        val wantToHit = InputView.wantToHit(player.name)
        if (wantToHit) {
            player.hit(dealer.draw())
            OutputView.handNotice(player)
        } else {
            player.stand()
        }
    }

    fun endRound() {
        OutputView.resultNotice(players)
    }
}
