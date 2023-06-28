package blackjack.scorerule.view

import blackjack.common.view.CommonOutputView

object ScoreStatusNoticer {
    fun noticeInitialStatus(playerNames: String, allStatusWithDealer: List<ScorePlayerStatus>) {
        CommonOutputView.beginNameNotice(playerNames)
        ScoreOutputView.roundBeginNotice(allStatusWithDealer)
    }

    fun noticeResultStatus(dealerStatus: ScorePlayerStatus, scorePlayerStatusList: List<ScorePlayerStatus>) {
        ScoreOutputView.roundResultNotice(dealerStatus, scorePlayerStatusList)
        ScoreOutputView.dealerResultNotice(dealerStatus)
        ScoreOutputView.playerResultNotice(scorePlayerStatusList)
    }
}
