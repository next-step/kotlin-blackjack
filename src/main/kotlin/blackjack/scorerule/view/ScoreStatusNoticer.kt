package blackjack.scorerule.view

import blackjack.common.view.OutputView

object ScoreStatusNoticer {
    fun noticeInitialStatus(playerNames: String, allStatusWithDealer: List<ScorePlayerStatus>) {
        OutputView.beginNameNotice(playerNames)
        ScoreOutputView.roundBeginNotice(allStatusWithDealer)
    }

    fun noticeResultStatus(dealerStatus: ScorePlayerStatus, scorePlayerStatusList: List<ScorePlayerStatus>) {
        ScoreOutputView.roundResultNotice(dealerStatus, scorePlayerStatusList)
        ScoreOutputView.dealerResultNotice(dealerStatus)
        ScoreOutputView.playerResultNotice(scorePlayerStatusList)
    }
}
