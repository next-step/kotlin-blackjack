package service

import domain.participant.Dealer
import domain.participant.ParticipantHand
import domain.participant.Player

class BlackjackWinnerService {
    fun winner(
        players: List<Player>,
        dealer: Dealer,
    ): Map<Player, Boolean> {
        val result = mutableMapOf<Player, Boolean>()

        players.forEach {
            if (dealer.score() > ParticipantHand.BLACKJACK_MAX_SCORE) {
                result[it] = true
            } else if (it.score() > ParticipantHand.BLACKJACK_MAX_SCORE) {
                result[it] = false
            } else {
                result[it] = it.score() >= dealer.score()
            }
        }

        return result
    }
}
