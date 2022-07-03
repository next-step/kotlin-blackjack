package blackjack.domain

enum class MatchResult {
    WIN, LOSE, DRAW;

    fun inverse() = when (this) {
        WIN -> LOSE
        DRAW -> DRAW
        LOSE -> WIN
    }
}
