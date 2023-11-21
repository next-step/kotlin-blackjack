package blackjack_dealer.entity

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant

enum class ParticipantResultState(val state: String) {
    WIN("승"), LOSE("패"), DRAW("무");

    companion object {
        fun of(dealer: Dealer, participant: Participant): ParticipantResultState {
            val dealerScore = dealer.getCurrentCards().getCurrentScore()
            val participantScore = participant.getCurrentCards().getCurrentScore()
            if (dealerScore > 21) return WIN
            return when {
                dealerScore - participantScore > 0 -> LOSE
                dealerScore - participantScore < 0 -> WIN
                else -> DRAW
            }
        }
    }
}
