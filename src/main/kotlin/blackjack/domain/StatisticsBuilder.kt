package blackjack.domain

interface Statistics

abstract class StatisticsBuilder<T : Statistics> {
    open fun onWin() {}
    open fun onLose() {}
    open fun onDraw() {}

    open fun onWin(player: Player) {
        onWin()
    }

    open fun onLose(player: Player) {
        onLose()
    }

    open fun onDraw(player: Player) {
        onDraw()
    }

    abstract fun build(): T
}

sealed class MatchResult {
    abstract fun applyTo(builder: StatisticsBuilder<*>, player: Player)

    data object Win : MatchResult() {
        override fun applyTo(builder: StatisticsBuilder<*>, player: Player) {
            builder.onWin(player)
        }
    }

    data object Lose : MatchResult() {
        override fun applyTo(builder: StatisticsBuilder<*>, player: Player) {
            builder.onLose(player)
        }
    }

    data object Draw : MatchResult() {
        override fun applyTo(builder: StatisticsBuilder<*>, player: Player) {
            builder.onDraw(player)
        }
    }

    companion object {
        fun from(dealer: Dealer, player: Player): MatchResult {
            return when (player.matchOf(dealer)) {
                MatchType.WIN -> Win
                MatchType.LOSE -> Lose
                MatchType.DRAW -> Draw
            }
        }
    }
}


fun <T : Statistics> Dealer.calculateStatistics(
    players: List<Player>,
    builder: StatisticsBuilder<T>
): T {
    players.forEach { player ->
        val matchResult = MatchResult.from(this, player)
        matchResult.applyTo(builder, player)
    }
    return builder.build()
}
