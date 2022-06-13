package blackjack.domain.participant.vo

data class WinningScores(
    val values: List<WinningScore>
)

enum class WinningScore(val value: Int, val description: String) {
    WIN(1, "승"),
    LOSE(-1, "패"),
    DRAW(0, "무");

    companion object {
        fun valueOf(value: Int): WinningScore = when (value) {
            1 -> WIN
            -1 -> LOSE
            0 -> DRAW
            else -> throw IllegalArgumentException("변환할수 없는 값입니다. $value")
        }
    }
}
