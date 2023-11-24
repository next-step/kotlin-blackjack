package enum

enum class GameResult(val description: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    companion object {
        fun determine(playerScore: Int, dealerScore: Int, maximumScore: Int): GameResult {
            return when {
                playerScore > maximumScore -> LOSE
                dealerScore > maximumScore -> WIN
                playerScore > dealerScore -> WIN
                playerScore == dealerScore -> DRAW
                else -> LOSE
            }
        }
    }
}
