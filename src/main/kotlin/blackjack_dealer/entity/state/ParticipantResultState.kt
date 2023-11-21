package blackjack_dealer.entity.state

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant

enum class ParticipantResultState(val state: String) {
    WIN("승"), LOSE("패"), DRAW("무");

    companion object {
        private const val BLACK_JACK = 21
        private const val DEFAULT_VALUE = 0

        fun of(dealer: Dealer, participant: Participant): ParticipantResultState {
            val dealerScore = dealer.getCurrentCards().getCurrentScore()
            val participantScore = participant.getCurrentCards().getCurrentScore()
            if (dealerScore > BLACK_JACK) return WIN
            if (participant.getCurrentGamerState() == GamerCurrentState.BUST) return LOSE
            return when {
                dealerScore - participantScore > 0 -> LOSE
                dealerScore - participantScore < 0 -> WIN
                else -> DRAW
            }
        }

        fun getParticipantResultStateGroup(groupingByParticipantResult: Map<ParticipantResultState, Int>) =
            values().associateWith { groupingByParticipantResult.getOrDefault(it, DEFAULT_VALUE) }
    }
}
