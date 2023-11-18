package blackjack.ui.printer

import blackjack.domain.Participant

@JvmInline
value class ParticipantPrinter(private val participant: Participant) {
    fun print(): String {
        val cardString = ParticipantCardsPrinter(participant.participantCards).print()
        val resultString = "결과: ${participant.participantCards.getCurrentScore()}"
        return "${participant.name} 카드: $cardString - $resultString"
    }
}
