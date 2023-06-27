package blackjack.bet

import blackjack.bet.domain.BetPlayer
import blackjack.bet.domain.BlackJackBetTable
import blackjack.bet.view.BetOutputView
import blackjack.bet.view.BetPlayerStatus
import blackjack.bet.view.BetStatusNoticer
import blackjack.common.view.InputView
import blackjack.common.view.OutputView

class BlackJackBetGame(playerNames: List<String>) {

    private val players = playerNames.map { BetPlayer(it) }
    private val table = BlackJackBetTable(players)

    fun startGame() {
        table.beginRound()
        val playersName = table.getPlayersName()
        val allStatusWithDealer = table.getRoundStartedStatus()
        BetStatusNoticer.noticeInitialStatus(playersName, allStatusWithDealer)
    }

    fun processRound() {
        table.executePlayerTurns(
            players,
            wantToHit = { name -> InputView.wantToHit(name) },
            handNotice = { player -> BetOutputView.handNotice(BetPlayerStatus.of(player)) },
            cantDrawMoreException = { name -> OutputView.canNotDrawMoreWarn(name) }
        )
        table.executeDealerTurn { OutputView.dealerAddNotice() }
        table.checkGameResult()
    }

    fun endGame() {
        val allStatusWithDealer = table.getAllStatusWithDealer()
        BetStatusNoticer.noticeResultStatus(allStatusWithDealer)
    }

    fun chargePhase(chargeRequest: (String) -> Int) {
        players.forEach {
            it.chargeWallet(chargeRequest.invoke(it.name))
        }
    }
}
