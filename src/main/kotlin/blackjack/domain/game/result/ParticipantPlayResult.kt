package blackjack.domain.game.result

import blackjack.domain.participant.Participant
import blackjack.domain.state.FinishState
import blackjack.domain.state.finish.Blackjack
import blackjack.domain.state.finish.Bust
import blackjack.domain.state.finish.Stay

data class ParticipantPlayResult(val participant: Participant, val finishState: FinishState) {

    fun isWinner(otherFinishState: FinishState): Boolean = when (finishState) {
        is Blackjack -> true
        is Bust -> false
        is Stay -> finishState.resultScore() > otherFinishState.resultScore()
        else -> false
    }
}
