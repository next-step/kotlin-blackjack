package blackjack.bet.view

import blackjack.common.view.OutputView

object BetStatusNoticer {
    fun noticeInitialStatus(playerNames: String, allStatusWithDealer: List<BetPlayerStatus>) {
        OutputView.beginNameNotice(playerNames)
        BetOutputView.roundBeginNotice(allStatusWithDealer)
    }

    fun noticeResultStatus(allStatusWithDealer: List<BetPlayerStatus>) {
        BetOutputView.roundResultNotice(allStatusWithDealer)
        BetOutputView.noticeEveryIncome(allStatusWithDealer)
    }
}
