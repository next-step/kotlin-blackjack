package blackjack.domain

enum class MatchStatus(private val revenueRatio: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    PUSH(0.0);

    fun getRevenueFrom(betAmount: BetAmount): Revenue = Revenue((betAmount.value * revenueRatio).toInt())
}
