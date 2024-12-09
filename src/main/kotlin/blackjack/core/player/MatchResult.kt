package blackjack.core.player

enum class MatchResult(val result: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    fun reverse(): MatchResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }
}
