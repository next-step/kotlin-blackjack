package enum

import domain.BlackjackRules

enum class GameResult {
    WIN,
    LOSE,
    DRAW;

    companion object {
        fun determineForResultOfPlayer(playerTotalScore: Int, dealerTotalScore: Int): GameResult {
            return when {
                playerTotalScore > BlackjackRules.MAXIMUM_SCORE -> LOSE
                dealerTotalScore > BlackjackRules.MAXIMUM_SCORE -> WIN
                playerTotalScore > dealerTotalScore -> WIN
                playerTotalScore == dealerTotalScore -> DRAW
                else -> LOSE
            }
        }
    }
}
