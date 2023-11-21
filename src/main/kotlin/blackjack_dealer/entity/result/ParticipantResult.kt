package blackjack_dealer.entity.result

import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.state.ParticipantResultState

data class ParticipantResult(
    val name: String,
    val resultState: ParticipantResultState
) {
    companion object {
        fun createParticipantResults(participants: Participants, dealer: Dealer): List<ParticipantResult> {
            return participants.map { participant ->
                val participantState = ParticipantResultState.of(dealer, participant)
                ParticipantResult(name = participant.getGamerName(), resultState = participantState)
            }
        }

        fun groupingAndCountParticipantsResult(participantsResult: List<ParticipantResult>) =
            participantsResult
                .groupingBy { it.resultState }
                .eachCount()
    }
}
