package blackjack.domain

sealed interface MatchStatus {
    val revenueRatio: Double

    fun getRevenueFrom(betAmount: BetAmount): Revenue = Revenue((betAmount.value * revenueRatio).toInt())

    object Blackjack : MatchStatus {
        override val revenueRatio: Double = 1.5
    }

    object Win : MatchStatus {
        override val revenueRatio: Double = 1.0
    }

    object Lose : MatchStatus {
        override val revenueRatio: Double = -1.0
    }

    object Push : MatchStatus {
        override val revenueRatio: Double = 0.0
    }
}
