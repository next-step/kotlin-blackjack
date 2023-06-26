package blackjack.view

object TableStatusNoticer {
    fun noticeInitialStatus(playerNames: String, allStatusWithDealer: List<PlayerStatus>) {
        OutputView.beginNameNotice(playerNames)
        OutputView.roundBeginNotice(allStatusWithDealer)
    }

    fun noticeResultStatus(dealerStatus: PlayerStatus, playerStatusList: List<PlayerStatus>) {
        OutputView.roundResultNotice(dealerStatus, playerStatusList)
        OutputView.dealerResultNotice(dealerStatus)
        OutputView.playerResultNotice(playerStatusList)
    }
}
