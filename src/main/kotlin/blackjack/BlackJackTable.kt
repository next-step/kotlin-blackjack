package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackTable(
    private val players: Array<Player>,
    private val dealer: Dealer = Dealer()
) {
    fun beginRound() {
        dealer.initializeRound(players)
        OutputView.roundBeginNotice(players)
    }

    fun executePlayerTurns(player: Array<Player>) {
        player.forEach(::proceedPlayerTurns)
    }

    private fun proceedPlayerTurns(player: Player) {
        while (player.ableToDraw) {
            resolveDrawPhase(player)
        }
    }

    private fun resolveDrawPhase(player: Player) {
        val wantToHit = InputView.wantToHit(player.name)
        if (wantToHit) {
            player.hit(dealer.draw())
            OutputView.handNotice(player)
        } else {
            player.stand()
        }
    }

    fun endRound() {
        OutputView.roundResultNotice(players)
    }
}
