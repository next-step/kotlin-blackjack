package blackjack.model.candidate

class CandidateGameResult(
    val candidate: Candidate,
    val winCount: Int,
    val lostCount: Int,
) {

    val candidateName
        get() = candidate.name

    companion object {
        private const val LOST_DECISION_BOUNDARY_SCORE = 21

        fun of(candidate: Candidate, other: Candidate): CandidateGameResult {
            if (other.isAllScoreGreaterThan(LOST_DECISION_BOUNDARY_SCORE)) {
                return ofWin(candidate)
            }

            if (candidate.isAllScoreGreaterThan(LOST_DECISION_BOUNDARY_SCORE)) {
                return ofLost(candidate)
            }

            if (candidate.beats(other, LOST_DECISION_BOUNDARY_SCORE)) {
                return ofWin(candidate)
            }

            if (other.beats(candidate, LOST_DECISION_BOUNDARY_SCORE)) {
                return ofLost(candidate)
            }

            return ofDraw(candidate)
        }

        private fun ofWin(candidate: Candidate): CandidateGameResult {
            return CandidateGameResult(candidate, 1, 0)
        }

        private fun ofLost(candidate: Candidate): CandidateGameResult {
            return CandidateGameResult(candidate, 0, 1)
        }

        private fun ofDraw(candidate: Candidate): CandidateGameResult {
            return CandidateGameResult(candidate, 0, 0)
        }
    }
}
