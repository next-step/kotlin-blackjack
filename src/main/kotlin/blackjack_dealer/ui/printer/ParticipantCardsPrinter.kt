package blackjack_dealer.ui.printer

import blackjack_dealer.entity.GamerCards

@JvmInline
value class ParticipantCardsPrinter(private val printer: String) {
    companion object {
        fun print(gamerCards: GamerCards): String {
            val resultString = gamerCards.joinToString { card -> CardPrinter.print(card) }
            return ParticipantCardsPrinter(resultString).printer
        }
    }
}
