package blackjack.bet.view

object BetOutputView {
    fun roundBeginNotice(status: List<BetPlayerStatus>) {
        status.forEach(BetOutputView::handNotice)
        println()
    }

    fun handNotice(status: BetPlayerStatus) {
        println("${status.name}카드: ${status.handRepresent}")
    }

    fun roundResultNotice(allStatusWithDealer: List<BetPlayerStatus>) {
        println()
        allStatusWithDealer.forEach {
            println("${it.name}카드: ${it.handRepresent} - 결과: ${it.optimalValue}")
        }
    }

    fun noticeEveryIncome(allStatusWithDealer: List<BetPlayerStatus>) {
        println("\n## 최종 수익")
        allStatusWithDealer.forEach {
            println("${it.name}: ${it.wallet.income()}")
        }
    }
}
