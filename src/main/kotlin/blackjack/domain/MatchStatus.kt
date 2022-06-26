package blackjack.domain

sealed interface MatchStatus {
    data class Dealer(val win: Int, val push: Int, val lose: Int) : MatchStatus {
        companion object {
            fun from(matchStatusMap: Map<Player, Int>): Dealer {
                return Dealer(
                    win = matchStatusMap[Win] ?: 0,
                    push = matchStatusMap[Push] ?: 0,
                    lose = matchStatusMap[Lose] ?: 0
                )
            }
        }
    }

    sealed interface Player : MatchStatus {
        val revenueRatio: Double

        fun opposite(): Player

        fun getRevenueFrom(betAmount: BetAmount): Revenue = Revenue((betAmount.value * revenueRatio).toInt())
    }

    object Blackjack : Player {
        override val revenueRatio: Double = 1.5

        override fun opposite(): Player = this
    }

    object Win : Player {
        override val revenueRatio: Double = 1.0

        override fun opposite(): Player = Lose
    }

    object Lose : Player {
        override val revenueRatio: Double = -1.0

        override fun opposite(): Player = Win
    }

    object Push : Player {
        override val revenueRatio: Double = 0.0

        override fun opposite(): Player = this
    }
}
