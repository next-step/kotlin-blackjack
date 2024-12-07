package blackjack.domain

data class ResultStatistics(
    val winCount: Int = 0,
    val loseCount: Int = 0,
    val drawCount: Int = 0,
) {
    fun increment(matchType: MatchType): ResultStatistics {
        return when (matchType) {
            MatchType.WIN -> copy(winCount = winCount + 1)
            MatchType.LOSE -> copy(loseCount = loseCount + 1)
            MatchType.DRAW -> copy(drawCount = drawCount + 1)
        }
    }

    fun merge(matchToStatistics: ResultStatistics): ResultStatistics {
        return copy(
            winCount = winCount + matchToStatistics.winCount,
            loseCount = loseCount + matchToStatistics.loseCount,
            drawCount = drawCount + matchToStatistics.drawCount,
        )
    }

    companion object Constant {
        val WIN_STATISTICS = ResultStatistics(winCount = 1)
        val LOSE_STATISTICS = ResultStatistics(loseCount = 1)
        val DRAW_STATISTICS = ResultStatistics(drawCount = 1)
    }
}
