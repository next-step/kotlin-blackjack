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
        val playerNames = players.filter { it.name != "딜러" }.joinToString { it.name }
        OutputView.beginNameNotice(playerNames)
        OutputView.roundBeginNotice(dealer.getParticipantInitialStatus(players))
    }

    fun executePlayerTurns(player: Array<Player>) {
        player.forEach(::proceedPlayerTurns)
    }

    private fun proceedPlayerTurns(player: Player) {
        while (player.wantToDraw()) {
            val wantToHit = InputView.wantToHit(player.name)
            player.drawPhase(wantToHit, deckManager) { OutputView.handNotice(PlayerStatus.of(player)) }
        }
    }

    fun checkScoreBoard() {
        val checker = GameResultChecker(dealer)
        dealer.drawPhase(deckManager = deckManager) { OutputView.dealerAddNotice() }
        checker.determineGameResult(players)
    }

    fun endRound() {
        val (dealerStatus, playerStatusList) = dealer.getParticipantStatus(players)
        OutputView.roundResultNotice(dealerStatus, playerStatusList)
        OutputView.dealerResultNotice(dealerStatus)
        OutputView.playerResultNotice(playerStatusList)
    }
}
