package blackjack.domain.game

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.ParticipantStatus
import blackjack.domain.participant.Player

data class Result(
    val scoreMap: MutableMap<Participant, Score> = mutableMapOf(),
) {

    fun checkWinner(dealer: Dealer, player: Player) {
        if (dealer.status == ParticipantStatus.BUST) {
            win(player)
            lose(dealer)
            return
        }
        if (player.status == ParticipantStatus.BUST) {
            win(dealer)
            lose(player)
            return
        }

        if (dealer.score() > player.score()) {
            win(dealer)
            lose(player)
        }
        if (dealer.score() < player.score()) {
            win(player)
            lose(dealer)
        }
    }

    private fun win(participant: Participant) {
        val score = scoreMap.getOrPut(participant) { Score() }
        score.win += 1
    }

    private fun lose(participant: Participant) {
        val score = scoreMap.getOrPut(participant) { Score() }
        score.lose += 1
    }
}
