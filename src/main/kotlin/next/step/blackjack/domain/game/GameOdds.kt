package next.step.blackjack.domain.game

enum class GameOdds(val odds: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    TIE(0.0),
    LOSE(-1.0);

    companion object {
        fun from(gameResult: GameResult, isBlackjack: Boolean): GameOdds = when (gameResult) {
            GameResult.WIN -> if (isBlackjack) BLACKJACK else WIN
            GameResult.LOSE -> LOSE
            else -> TIE
        }
    }
}