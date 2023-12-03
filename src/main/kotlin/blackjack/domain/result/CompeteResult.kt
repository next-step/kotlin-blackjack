package blackjack.domain.result

enum class CompeteResult(val text: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    fun opposite(): CompeteResult {
        return when (this) {
            WIN -> LOSE
            DRAW -> DRAW
            LOSE -> WIN
        }
    }
}
