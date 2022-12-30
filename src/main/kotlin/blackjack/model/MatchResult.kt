package blackjack.model

enum class MatchResult {
    WIN, LOSE, PUSH;

    companion object {
        fun of(score: Int, otherScore: Int): MatchResult {
            if (score == otherScore) {
                return PUSH
            }
            if (score > otherScore) {
                return WIN
            }
            return LOSE
        }
    }
}
