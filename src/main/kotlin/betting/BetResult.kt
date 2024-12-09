package betting

data class BetResult (
    val bet: Bet,
    val winning: Winning = Winning(),
)
