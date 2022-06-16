package blackjack.domain.winning

import blackjack.domain.score.PlayerScore

class WinningStat(
    playerScores: List<PlayerScore>,
    dealerScore: PlayerScore,
) {
    private val scores: List<PlayerScore> = playerScores + dealerScore
    private val dealerScore: Int = dealerScore.score

    fun indicator(): List<WinningResult> {
        return scores.map {
            WinningResult(it.player, win(it.score), loose(it.score), tie(it.score))
        }
    }

    fun dealerWinning(): Boolean {
        if (dealerScore >= BLACK_JACK_SCORE) return false
        return true
    }

    private fun win(score: Int): Int {
        return scores.count() { it -> it.score > score }
    }

    private fun loose(score: Int): Int {
        return scores.count() { it -> it.score < score }
    }

    private fun tie(score: Int): Int {
        val sameScoreCount = scores.count() { it -> it.score == score }
        return sameScoreCount - SELF_SAME_COUNT
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val SELF_SAME_COUNT = 1
    }
}
