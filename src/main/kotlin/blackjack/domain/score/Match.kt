package blackjack.domain.score

enum class Match(val description: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    operator fun not(): Match {
        return when (this) {
            WIN -> LOSE
            DRAW -> DRAW
            LOSE -> WIN
        }
    }
}
