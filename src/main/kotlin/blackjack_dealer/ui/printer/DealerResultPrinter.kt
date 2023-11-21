package blackjack_dealer.ui.printer

import blackjack_dealer.domain.Dealer

@JvmInline
value class DealerResultPrinter(private val printer: String) {
    companion object {
        fun print(dealer: Dealer): String {
            val cardString = ParticipantCardsPrinter.print(gamerCards = dealer.getCurrentCards())
            val resultString = "결과: ${dealer.getCurrentCards().getCurrentScore()}"
            val result = "${dealer.getDealerName()} 카드: $cardString - $resultString"
            return DealerResultPrinter(result).printer
        }
    }
}
