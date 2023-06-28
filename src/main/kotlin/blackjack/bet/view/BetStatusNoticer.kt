package blackjack.bet.view

import blackjack.common.view.CommonOutputView

object BetStatusNoticer {
    fun noticeInitialStatus(playerNames: String, allStatusWithDealer: List<BetPlayerStatus>) {
        CommonOutputView.beginNameNotice(playerNames)
        BetOutputView.roundBeginNotice(allStatusWithDealer)
    }

    fun noticeResultStatus(allStatusWithDealer: List<BetPlayerStatus>) {
        BetOutputView.roundResultNotice(allStatusWithDealer)
        BetOutputView.noticeEveryIncome(allStatusWithDealer)
    }
}
