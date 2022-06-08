package blackjack.domain.game

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.ParticipantStatus
import blackjack.domain.participant.Player

data class Result(
    val score: MutableMap<Participant, Int> = mutableMapOf(),
) {

    fun check(dealer: Dealer, player: Player) {
        if (dealer.status == ParticipantStatus.BUST) {
            win(player)
            return
        }
        if (player.status == ParticipantStatus.BUST) {
            win(dealer)
            return
        }

        if (dealer.score() > player.score()) {
            win(dealer)
        } else {
            win(player)
        }
    }

    private fun win(participant: Participant) {
        score[participant] = score.getOrDefault(participant, 0) + 1
    }
}
