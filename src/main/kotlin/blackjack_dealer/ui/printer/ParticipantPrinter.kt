package blackjack_dealer.ui.printer

import blackjack_dealer.domain.Participant

@JvmInline
value class ParticipantPrinter(val printer: String) {
    companion object {
        fun print(participant: Participant): String {
            val result =
                "${participant.getParticipantName()}카드: ${ParticipantCardsPrinter.print(participant.getCurrentCards())}"
            return ParticipantPrinter(result).printer
        }
    }
}
