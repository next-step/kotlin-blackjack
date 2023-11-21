package blackjack_dealer.ui.printer

import blackjack_dealer.domain.Dealer

@JvmInline
value class DealerPrinter(val printer: String) {
    companion object {
        fun print(participant: Dealer): String {
            val result =
                "${participant.getDealerName()}카드: ${ParticipantCardsPrinter.print(participant.getCurrentCards())}"
            return DealerPrinter(result).printer
        }
    }
}
