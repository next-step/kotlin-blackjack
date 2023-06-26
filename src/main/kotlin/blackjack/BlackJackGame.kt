package blackjack

import blackjack.domain.Player
import blackjack.service.BlackJackTable
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.PlayerStatus
import blackjack.view.TableStatusNoticer

class BlackJackGame(private val players: Array<Player>) {
    private val table = BlackJackTable(players)
    fun startGame() {
        table.beginRound()
        val playersName = table.getPlayersName()
        val allStatusWithDealer = table.getAllStatusWithDealer()
        TableStatusNoticer.noticeInitialStatus(playersName, allStatusWithDealer)
    }

    fun processRound() {
        table.executePlayerTurns(
            players,
            wantToHit = { name -> InputView.wantToHit(name) },
            handNotice = { player -> OutputView.handNotice(PlayerStatus.of(player)) }
        )
        table.executeDealerTurn { OutputView.dealerAddNotice() }
        table.checkGameResult()
    }

    fun endGame() {
        val dealerStatus = table.getDealerStatus()
        val playerStatusList = players.map(PlayerStatus::of)
        TableStatusNoticer.noticeResultStatus(dealerStatus, playerStatusList)
    }
}
