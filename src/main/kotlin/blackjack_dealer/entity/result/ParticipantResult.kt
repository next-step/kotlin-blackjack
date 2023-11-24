package blackjack_dealer.entity.result

import blackjack_dealer.entity.state.ParticipantResultState

data class ParticipantResult(
    val name: String,
    val resultState: ParticipantResultState
) {
    companion object {
        fun groupingAndCountParticipantsResult(participantsResult: List<ParticipantResult>) =
            participantsResult
                .groupingBy { it.resultState }
                .eachCount()
    }
}
