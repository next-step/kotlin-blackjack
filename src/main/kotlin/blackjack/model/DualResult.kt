package blackjack.model

enum class DualResult {
    WIN, PUSH, LOSE;

    companion object {
        fun of(score: Int, otherScore: Int): DualResult {
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
