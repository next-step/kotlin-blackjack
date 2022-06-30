package blackjack.dto

import blackjack.model.candidate.Candidate
import blackjack.model.candidate.Dealer
import blackjack.model.candidate.Player

class CandidateGameResultDto(
    val candidate: Candidate,
    val profit: Double,
) {

    val candidateName
        get() = candidate.name

    companion object {
        private const val LOST_DECISION_BOUNDARY_SCORE = 21
        private const val MULTIPLICATION_NUMBER_FOR_BLACK_JACK_PROFIT = 1.5
        private const val DRAW_PROFIT = 0

        fun of(player: Player, dealer: Dealer): CandidateGameResultDto =
            when {
                player.satisfyBlackJack() && dealer.satisfyBlackJack() -> ofDraw(player)
                player.satisfyBlackJack() -> ofBlackJack(player)
                dealer.isAllScoreGreaterThan(LOST_DECISION_BOUNDARY_SCORE) -> ofWin(player)
                player.isAllScoreGreaterThan(LOST_DECISION_BOUNDARY_SCORE) -> ofLost(player)
                player.beats(dealer, LOST_DECISION_BOUNDARY_SCORE) -> ofWin(player)
                dealer.beats(player, LOST_DECISION_BOUNDARY_SCORE) -> ofLost(player)
                else -> ofDraw(player)
            }

        private fun ofBlackJack(player: Player): CandidateGameResultDto {
            return CandidateGameResultDto(player, player.bettingAmount * MULTIPLICATION_NUMBER_FOR_BLACK_JACK_PROFIT)
        }

        private fun ofWin(player: Player): CandidateGameResultDto {
            return CandidateGameResultDto(player, player.bettingAmount.toDouble())
        }

        private fun ofLost(player: Player): CandidateGameResultDto {
            return CandidateGameResultDto(player, -player.bettingAmount.toDouble())
        }

        private fun ofDraw(player: Player): CandidateGameResultDto {
            return CandidateGameResultDto(player, DRAW_PROFIT.toDouble())
        }
    }
}
