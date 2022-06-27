package blackjack.model.candidate

class CandidateGameResult(
    val candidate: Candidate,
    val profit: Double,
) {

    val candidateName
        get() = candidate.name

    companion object {
        private const val LOST_DECISION_BOUNDARY_SCORE = 21
        private const val MULTIPLICATION_NUMBER_FOR_BLACK_JACK_PROFIT = 1.5
        private const val DRAW_PROFIT = 0

        fun of(player: Player, dealer: Dealer): CandidateGameResult {
            if (player.satisfyBlackJack() && dealer.satisfyBlackJack()) {
                return ofDraw(player)
            }

            if (player.satisfyBlackJack()) {
                return ofBlackJack(player)
            }

            if (dealer.isAllScoreGreaterThan(LOST_DECISION_BOUNDARY_SCORE)) {
                return ofWin(player)
            }

            if (player.isAllScoreGreaterThan(LOST_DECISION_BOUNDARY_SCORE)) {
                return ofLost(player)
            }

            if (player.beats(dealer, LOST_DECISION_BOUNDARY_SCORE)) {
                return ofWin(player)
            }

            if (dealer.beats(player, LOST_DECISION_BOUNDARY_SCORE)) {
                return ofLost(player)
            }

            return ofDraw(player)
        }

        private fun ofBlackJack(player: Player): CandidateGameResult {
            return CandidateGameResult(player, player.bettingAmount * MULTIPLICATION_NUMBER_FOR_BLACK_JACK_PROFIT)
        }

        private fun ofWin(player: Player): CandidateGameResult {
            return CandidateGameResult(player, player.bettingAmount.toDouble())
        }

        private fun ofLost(player: Player): CandidateGameResult {
            return CandidateGameResult(player, -player.bettingAmount.toDouble())
        }

        private fun ofDraw(player: Player): CandidateGameResult {
            return CandidateGameResult(player, DRAW_PROFIT.toDouble())
        }
    }
}
