package blackjack.domain.participant.type

enum class WinningScore(val value: Int) {
    WIN(1),
    LOSE(-1),
    DRAW(0);

    companion object {
        fun valueOf(value: Int): WinningScore = when (value) {
            1 -> WIN
            -1 -> LOSE
            0 -> DRAW
            else -> throw IllegalArgumentException("변환할수 없는 값입니다. $value")
        }
    }
}
