package blackjack.domain.participant.vo

data class WinningScores(
    val winCount: Int = 0,
    val loseCount: Int = 0,
    val drawCount: Int = 0
) {
    fun add(winningScore: WinningScore): WinningScores = when(winningScore) {
        WinningScore.WIN -> this.copy(winCount = winCount.inc())
        WinningScore.LOSE -> this.copy(loseCount = loseCount.inc())
        WinningScore.DRAW -> this.copy(drawCount = drawCount.inc())
    }
}


enum class WinningScore(val value: Int) {
    WIN(1),
    LOSE(-1),
    DRAW(0);

    companion object {
        fun valueOf(value: Int): WinningScore = when(value) {
            1 -> WIN
            -1 -> LOSE
            0 -> DRAW
            else -> throw IllegalArgumentException("변환할수 없는 값입니다. $value")
        }
    }
}

