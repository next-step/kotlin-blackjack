package blackjack_dealer.ui.printer

import blackjack_dealer.domain.Gamer

@JvmInline
value class GamerPrinter(val printer: String) {
    companion object {
        fun print(gamer: Gamer): String {
            val result =
                "${gamer.getGamerName()}카드: ${ParticipantCardsPrinter.print(gamer.getCurrentCards())}"
            return GamerPrinter(result).printer
        }
    }
}
