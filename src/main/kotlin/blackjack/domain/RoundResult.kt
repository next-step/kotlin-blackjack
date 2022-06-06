package blackjack.domain

sealed interface RoundResult {
    companion object {
        fun valueOf(value: Int): RoundResult = when(value) {
            1 -> Win
            -1 -> Lose
            0 -> Draw
            else -> throw IllegalArgumentException("")
        }
    }
}

object Win : RoundResult

object Lose : RoundResult

object Draw : RoundResult

data class RoundResults(
    val winCount: Int,
    val loseCount: Int,
    val drawCount: Int
) {
    fun add(roundResult: RoundResult): RoundResults = when(roundResult) {
        is Win -> this.copy(winCount = winCount.inc())
        is Lose -> this.copy(loseCount = loseCount.inc())
        is Draw -> this.copy(drawCount = drawCount.inc())
    }
}
