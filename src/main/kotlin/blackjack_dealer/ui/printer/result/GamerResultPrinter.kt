package blackjack_dealer.ui.printer.result

import blackjack_dealer.domain.Gamer
import blackjack_dealer.ui.printer.ParticipantCardsPrinter

@JvmInline
value class GamerResultPrinter(private val printer: String) {
    companion object {
        fun print(dealer: Gamer): String {
            val cardString = ParticipantCardsPrinter.print(gamerCards = dealer.getCurrentCards())
            val resultString = "결과: ${dealer.getCurrentCards().getCurrentScore()}"
            val result = "${dealer.getGamerName()} 카드: $cardString - $resultString"
            return GamerResultPrinter(result).printer
        }
    }
}
