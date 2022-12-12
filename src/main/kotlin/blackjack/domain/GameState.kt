package blackjack.domain

private const val LOSE_RATE = -1
private const val WIN_BLACKJACK_RATE = 1.5

enum class GameState(
    private val func: (Int) -> Double,
) {
    WIN({ betMoney -> betMoney.toDouble() }),
    WIN_BLACKJACK({ betMoney -> betMoney * WIN_BLACKJACK_RATE }),
    LOSE({ betMoney -> (betMoney * LOSE_RATE).toDouble() });

    fun benefit(bet: Bet): Double {
        return this.func(bet.money)
    }
}
