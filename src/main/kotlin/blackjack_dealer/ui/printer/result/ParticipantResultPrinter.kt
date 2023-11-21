package blackjack_dealer.ui.printer.result

import blackjack_dealer.domain.Participant
import blackjack_dealer.ui.printer.ParticipantCardsPrinter

@JvmInline
value class ParticipantResultPrinter(private val printer: String) {
    companion object {
        fun print(participant: Participant): String {
            val cardString = ParticipantCardsPrinter.print(gamerCards = participant.getCurrentCards())
            val resultString = "결과: ${participant.getCurrentCards().getCurrentScore()}"
            val result = "${participant.getParticipantName()} 카드: $cardString - $resultString"
            return ParticipantResultPrinter(result).printer
        }
    }
}
