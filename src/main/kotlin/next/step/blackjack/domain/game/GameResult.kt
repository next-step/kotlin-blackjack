package next.step.blackjack.domain.game

enum class GameResult(val desc: String) {
    WIN("승"),
    TIE("무"),
    LOSE("패"),
    UNDECIDED("미정");

    fun opposite() = when (this) {
        WIN -> LOSE
        TIE -> TIE
        LOSE -> WIN
        UNDECIDED -> UNDECIDED
    }
}