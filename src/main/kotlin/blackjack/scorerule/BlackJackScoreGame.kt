package blackjack.scorerule

import blackjack.common.view.CommonInputView
import blackjack.common.view.CommonOutputView
import blackjack.scorerule.domain.BlackJackScoreTable
import blackjack.scorerule.domain.ScorePlayer
import blackjack.scorerule.view.ScoreOutputView
import blackjack.scorerule.view.ScorePlayerStatus
import blackjack.scorerule.view.ScoreStatusNoticer

class BlackJackScoreGame(playerNames: List<String>) {

    private val players = playerNames.map { ScorePlayer(it) }.toTypedArray()
    private val table = BlackJackScoreTable(players)

    fun startGame() {
        table.beginRound()
        val playersName = table.getPlayersName()
        val allStatusWithDealer = table.getAllStatusWithDealer()
        ScoreStatusNoticer.noticeInitialStatus(playersName, allStatusWithDealer)
    }

    fun processRound() {
        table.executePlayerTurns(
            players,
            wantToHit = { name -> CommonInputView.wantToHit(name) },
            handNotice = { player -> ScoreOutputView.handNotice(ScorePlayerStatus.of(player)) }
        )
        table.executeDealerTurn { CommonOutputView.dealerAddNotice() }
        table.checkGameResult()
    }

    fun endGame() {
        val dealerStatus = table.getDealerStatus()
        val scorePlayerStatusList = players.map(ScorePlayerStatus::of)
        ScoreStatusNoticer.noticeResultStatus(dealerStatus, scorePlayerStatusList)
    }
}
