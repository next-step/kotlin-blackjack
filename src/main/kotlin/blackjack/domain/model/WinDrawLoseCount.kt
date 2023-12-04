package blackjack.domain.model

data class WinDrawLoseCount(
    val winCount: Int,
    val drawCount: Int,
    val loseCount: Int
) {
    init {
        require(winCount + loseCount + drawCount > TOTAL_MIN_COUNT) {
            "모든 결과의 합이 ${TOTAL_MIN_COUNT}보다 같거나 작을 수 없습니다."
        }
    }
    companion object {

        private const val TOTAL_MIN_COUNT = 0
        fun of(winCount: Int, drawCount: Int, loseCount: Int): WinDrawLoseCount = WinDrawLoseCount(winCount, drawCount, loseCount)
    }
}
