package blackjack.domain

sealed interface MatchStatus {
    sealed interface Player : MatchStatus {
        val revenueRatio: Double

        fun getRevenueFrom(betAmount: BetAmount): Revenue = Revenue((betAmount.value * revenueRatio).toInt())
    }

    object Blackjack : Player {
        override val revenueRatio: Double = 1.5
    }

    object Win : Player {
        override val revenueRatio: Double = 1.0
    }

    object Lose : Player {
        override val revenueRatio: Double = -1.0
    }

    object Push : Player {
        override val revenueRatio: Double = 0.0
    }
}
