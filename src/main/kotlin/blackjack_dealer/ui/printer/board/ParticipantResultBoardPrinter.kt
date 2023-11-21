package blackjack_dealer.ui.printer.board

import blackjack_dealer.entity.result.ParticipantResult

@JvmInline
value class ParticipantResultBoardPrinter(private val printer: String) {
    companion object {
        fun print(participantResult: ParticipantResult): String {
            val resultState = participantResult.resultState.state
            val result = "${participantResult.name}: $resultState"
            return ParticipantResultBoardPrinter(result).printer
        }
    }
}
