package blackjack

enum class MatchResult(val korean: String) {
    WIN("승") {
        override val opponentResult: MatchResult
            get() = LOSE
    },
    DRAW("무") {
        override val opponentResult: MatchResult
            get() = DRAW
    },
    LOSE("패") {
        override val opponentResult: MatchResult
            get() = WIN
    };

    abstract val opponentResult: MatchResult
}
