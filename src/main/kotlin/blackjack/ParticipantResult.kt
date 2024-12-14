package blackjack

import blackjack.participant.Dealer
import blackjack.participant.Participant
import blackjack.participant.Player

data class ParticipantResult(
    val participant: Participant,
    val result: GameResult,
) {
    companion object {
        fun of(dealer: Dealer, player: Player): List<ParticipantResult> {
            return when {
                player.score() > dealer.score() -> listOf(
                        ParticipantResult(player, GameResult.WIN),
                        ParticipantResult(dealer, GameResult.LOSE),
                )
                player.score() < dealer.score() -> listOf(
                    ParticipantResult(dealer, GameResult.WIN),
                    ParticipantResult(player, GameResult.LOSE),
                )
                else -> listOf(
                    ParticipantResult(player, GameResult.DRAW),
                    ParticipantResult(dealer, GameResult.DRAW),
                )
            }
        }
    }
}