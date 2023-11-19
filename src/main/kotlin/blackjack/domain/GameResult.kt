package blackjack.domain

enum class GameResult(val value: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    fun reverse(): GameResult {
        if (this == WIN) {
            return LOSE
        }
        if (this == LOSE) {
            return WIN
        }
        return DRAW
    }
}
