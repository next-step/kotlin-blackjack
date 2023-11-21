package blackjack_dealer.ui.printer.board

import blackjack_dealer.entity.result.DealerResult

@JvmInline
value class DealerResultBoardPrinter(private val printer: String) {
    companion object {
        fun print(dealer: DealerResult): String {
            val result = "${dealer.name}: ${dealer.winCount}승 ${dealer.drawCount}무 ${dealer.loseCount}패"
            return DealerResultBoardPrinter(result).printer
        }
    }
}
