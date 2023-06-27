package blackjack.domain.gamestate

enum class Competition {
    WIN,
    LOSE,
    DRAW,
    ;

    fun toOpposite() = when (this) {
        WIN -> LOSE
        LOSE -> WIN
        DRAW -> DRAW
    }
}
