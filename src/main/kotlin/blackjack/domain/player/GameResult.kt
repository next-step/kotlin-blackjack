package blackjack.domain.player

enum class GameResult(val korName: String) {
    WIN("승"), LOOSE("패"), TIE("무"),
    ;

    companion object {
        fun valueOfOpposition(gameResult: GameResult): GameResult {
            return when (gameResult) {
                WIN -> LOOSE
                LOOSE -> WIN
                TIE -> TIE
            }
        }

        fun valueByCompare(dealerScore: Int, player: Player): GameResult {
            val playerScore = player.cards.getOptimizedScore()
            return when {
                (dealerScore > PlayerStatus.BLACK_JACK_SCORE && player.getPlayerStatus() != PlayerStatus.BUST) ||
                    (playerScore <= PlayerStatus.BLACK_JACK_SCORE && dealerScore < playerScore) -> WIN
                (playerScore == dealerScore) -> TIE
                else -> LOOSE
            }
        }
    }
}
