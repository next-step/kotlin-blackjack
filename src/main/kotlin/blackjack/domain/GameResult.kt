package blackjack.domain

enum class GameResult(val label: String) {
    WIN("승"), LOSE("패"), DRAW("무");

    companion object {
        fun of(score: Int): GameResult {
            return when {
                score > 0 -> WIN
                score == 0 -> DRAW
                else -> LOSE
            }
        }
    }
}
