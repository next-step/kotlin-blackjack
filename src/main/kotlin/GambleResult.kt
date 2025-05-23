data class GambleResult(
    val win: Int = 0,
    val lose: Int = 0,
    val draw: Int = 0,
) {
    operator fun plus(other: GameResult): GambleResult {
        return when (other) {
            GameResult.WIN -> copy(win = win + 1)
            GameResult.LOSE -> copy(lose = lose + 1)
            GameResult.DRAW -> copy(draw = draw + 1)
        }
    }

    companion object {
        fun from(value: GameResult): GambleResult {
            return when (value) {
                GameResult.WIN -> GambleResult(win = 1)
                GameResult.LOSE -> GambleResult(lose = 1)
                GameResult.DRAW -> GambleResult(draw = 1)
            }
        }
    }
}
