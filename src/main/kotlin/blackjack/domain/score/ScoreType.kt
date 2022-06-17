package blackjack.domain.score

enum class ScoreType {
    WIN,
    DRAW,
    LOSE;

    fun opposite(): ScoreType = when (this) {
        WIN -> LOSE
        DRAW -> DRAW
        LOSE -> WIN
    }
}
