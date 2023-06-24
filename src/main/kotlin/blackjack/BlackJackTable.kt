package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.PlayerStatus

class BlackJackTable(private val players: Array<Player>) {

    private val dealer: Dealer = Dealer()
    private val deckManager: DeckManager = DeckManager()

    fun beginRound() {
        dealer.initializeRound(deckManager, players)
        OutputView.roundBeginNotice(dealer.getAllParticipantsStatus(players))
    }

    fun executePlayerTurns(player: Array<Player>) {
        player.forEach(::proceedPlayerTurns)
    }

    private fun proceedPlayerTurns(player: Player) {
        while (player.ableToDraw) {
            val wantToHit = InputView.wantToHit(player.name)
            player.drawPhase(wantToHit, deckManager) { OutputView.handNotice(PlayerStatus.of(player)) }
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
