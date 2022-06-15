package blackjack.domain.player

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
