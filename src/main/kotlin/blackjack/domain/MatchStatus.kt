package blackjack.domain

sealed interface MatchStatus {
    fun inverse(): MatchStatus

    data class Dealer(val win: Int, val push: Int, val lose: Int) : MatchStatus {
        override fun inverse(): MatchStatus = this

        companion object {
            fun from(matchStatusMap: Map<MatchStatus, Int>): Dealer {
                return Dealer(
                    win = matchStatusMap[Win] ?: 0,
                    push = matchStatusMap[Push] ?: 0,
                    lose = matchStatusMap[Lose] ?: 0
                )
            }
        }
    }

    object Win : MatchStatus {
        override fun inverse(): MatchStatus = Lose
    }

    object Lose : MatchStatus {
        override fun inverse(): MatchStatus = Win
    }

    object Push : MatchStatus {
        override fun inverse(): MatchStatus = this
    }
}
