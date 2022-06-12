package blackjack.domain.score

enum class Match {
    WIN,
    DRAW,
    LOSE;

    operator fun not(): Match {
        return when (this) {
            WIN -> LOSE
            DRAW -> DRAW
            LOSE -> WIN
        }
    }
}
