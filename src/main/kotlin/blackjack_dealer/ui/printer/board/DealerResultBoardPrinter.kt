package blackjack_dealer.ui.printer.board

import blackjack_dealer.entity.result.DealerResult

@JvmInline
value class DealerResultBoardPrinter(private val printer: String) {
    companion object {
        fun print(dealer: DealerResult): String {
            val result =
                "${dealer.name}: ${dealer.dealerResultCount.win}승 ${dealer.dealerResultCount.draw}무 ${dealer.dealerResultCount.lose}패"
            return DealerResultBoardPrinter(result).printer
        }
    }
}
