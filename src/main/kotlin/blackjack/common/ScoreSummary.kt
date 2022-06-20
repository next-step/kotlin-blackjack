package blackjack.common

import blackjack.domain.player.Dealer
import blackjack.domain.score.ScoreType
import blackjack.domain.score.Scores
import blackjack.view.ScoreName

class ScoreSummary(scores: Scores) {
    val values: List<Pair<String, String>> =
        listOf(Dealer.DEALER_NAME to getDealerScore(scores)) +
            scores.values.map { it.playerName to ScoreName.of(it.score) }

    private fun getDealerScore(playerScores: Scores): String {
        val win = playerScores.dealerScoreOf(ScoreType.WIN)
        val draw = playerScores.dealerScoreOf(ScoreType.DRAW)
        val lose = playerScores.dealerScoreOf(ScoreType.LOSE)

        return "$win $draw $lose".trim()
    }

    private fun Scores.dealerScoreOf(scoreType: ScoreType): String {
        val opposite = scoreType.opposite()

        return scoreType.toStringWithCount(values.count { it.score == opposite })
    }

    private fun ScoreType.toStringWithCount(count: Int): String =
        if (count > 0)
            "${count}${ScoreName.of(this)}"
        else
            ""
}
