package blackjack.bet

import blackjack.bet.domain.BetPlayer
import blackjack.bet.domain.BlackJackBetTable
import blackjack.bet.view.BetOutputView
import blackjack.bet.view.BetPlayerStatus
import blackjack.bet.view.BetStatusNoticer
import blackjack.common.view.CommonOutputView

class BlackJackBetGame(playerNames: List<String>) {

    private val players = playerNames.map { BetPlayer(it) }
    private val table = BlackJackBetTable(players)

    fun startGame() {
        table.beginRound()
        val playersName = table.getPlayersName()
        val allStatusWithDealer = table.getInitialStatus()
        BetStatusNoticer.noticeInitialStatus(playersName, allStatusWithDealer)
    }

    fun processRound(wantToHit: (String) -> Boolean) {
        table.executePlayerTurns(
            players,
            wantToHit = wantToHit,
            handNotice = { player -> BetOutputView.handNotice(BetPlayerStatus.of(player)) },
            cantDrawException = { name -> CommonOutputView.canNotDrawMoreWarn(name) }
        )
        table.executeDealerTurn { CommonOutputView.dealerAddNotice() }
        table.checkGameResult()
    }

    fun endGame() {
        val allStatusWithDealer = table.getAllStatus()
        BetStatusNoticer.noticeResultStatus(allStatusWithDealer)
    }

    fun chargePhase(chargeRequest: (String) -> Int) {
        players.forEach {
            it.charge(chargeRequest.invoke(it.name))
        }
    }
}
