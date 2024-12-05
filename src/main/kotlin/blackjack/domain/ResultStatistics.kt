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
}
