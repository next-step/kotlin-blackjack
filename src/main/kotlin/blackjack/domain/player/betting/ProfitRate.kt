package blackjack.domain.player.betting

enum class ProfitRate(
    private val earningRate: Double
) {
    BLACKJACK_WIN_PROFIT_RATE(1.5),
    BLACKJACK_DRAW_PROFIT_RATE(1.0),
    WIN_PROFIT_RATE(1.0),
    DRAW_PROFIT_RATE(1.0),
    LOSE_PROFIT_RATE(-1.0),
    BUST_PROFIT_RATE(-1.0);

    fun getProfit(betting: Betting): Profit =
        Profit(betting.amount * earningRate)
}

operator fun Betting.times(profitRate: ProfitRate): Profit =
    profitRate.getProfit(this)

operator fun ProfitRate.times(betting: Betting): Profit =
    getProfit(betting)
