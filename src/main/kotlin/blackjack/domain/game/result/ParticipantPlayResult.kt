package blackjack.domain.game.result

import blackjack.domain.participant.Participant
import blackjack.domain.state.FinishState

data class ParticipantPlayResult(val participant: Participant, val finishState: FinishState)
