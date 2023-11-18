package blackjack.ui.printer

import blackjack.entity.ParticipantCards

@JvmInline
value class ParticipantCardsPrinter(private val printer: String) {
    companion object {
        fun print(participantCards: ParticipantCards): String {
            val resultString = participantCards.joinToString { card -> CardPrinter.print(card) }
            return ParticipantCardsPrinter(resultString).printer
        }
    }
}
