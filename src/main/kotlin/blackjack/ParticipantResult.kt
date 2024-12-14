package blackjack

import blackjack.participant.Dealer
import blackjack.participant.Players

data class ParticipantResult(
    val dealer: Dealer,
    val players: Players,
) {
    companion object {
        fun of(
            dealer: Dealer,
            gamePlayers: Players,
        ): List<ParticipantResult> {

        }
    }
}
