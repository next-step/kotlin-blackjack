package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.PlayerStatus

class BlackJackTable(players: Array<Player>) {

    private val dealer: Dealer = Dealer(players = players)

    fun beginRound() {
        dealer.initializeRound()
        OutputView.roundBeginNotice(dealer.getAllPlayerStatus())
    }

    fun executePlayerTurns(player: Array<Player>) {
        player.forEach(::proceedPlayerTurns)
    }

    private fun proceedPlayerTurns(player: Player) {
        while (player.ableToDraw) {
            val wantToHit = InputView.wantToHit(player.name)
            player.drawPhase(wantToHit, dealer) { OutputView.handNotice(PlayerStatus.of(player)) }
        }
    }

    fun endRound() {
        val checker = GameResultChecker(dealer)
        dealer.drawAdditionalCard(deckManager) { OutputView.dealerAddNotice() }
        dealer.determineWinner(players, checker)
        val allPlayerStatus = dealer.getAllParticipantsStatus(players)
        OutputView.roundResultNotice(allPlayerStatus)
        OutputView.gameResultNotice(allPlayerStatus)
    }
}
