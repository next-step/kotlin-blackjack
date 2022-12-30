package blackjack.model

enum class GameResult {
    WIN, LOSE, PUSH;

    companion object {
        fun of(score: Int, otherScore: Int): GameResult {
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
