package blackjack.domain

data class GameResult(
    val playerResults: List<PlayerResult>,
) {
    val dealerWins: Int
        get() = playerResults.count { it.outcome == PlayerOutcome.LOSE }

    val dealerLosses: Int
        get() = playerResults.count { it.outcome == PlayerOutcome.WIN }

    val dealerDraws: Int
        get() = playerResults.count { it.outcome == PlayerOutcome.DRAW }
}
