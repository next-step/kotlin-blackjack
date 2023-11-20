package blackjack.ui.printer

import blackjack.domain.Participant

@JvmInline
value class ParticipantPrinter(private val printer: String) {
    companion object {
        fun print(participant: Participant): String {
            val cardString = ParticipantCardsPrinter.print(participantCards = participant.participantCards)
            val resultString = "결과: ${participant.participantCards.getCurrentScore()}"
            val result = "${participant.name} 카드: $cardString - $resultString"
            return ParticipantPrinter(result).printer
        }
    }
}
