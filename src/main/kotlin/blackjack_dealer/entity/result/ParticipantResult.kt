package blackjack_dealer.entity.result

import blackjack_dealer.entity.state.ParticipantResultState

data class ParticipantResult(
    val name: String,
    val resultState: ParticipantResultState
)
