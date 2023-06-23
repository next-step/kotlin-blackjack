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
            val wantToHit = InputView.wantToHit(player.name)
            player.drawPhase(wantToHit, dealer) { OutputView.handNotice(player) }
        }
    }

    fun endRound() {
        OutputView.roundResultNotice(players)
    }
}
