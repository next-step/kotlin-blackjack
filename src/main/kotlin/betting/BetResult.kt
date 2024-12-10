package betting

data class BetResult (
    val bet: Bet,
    val winning: Winning = Winning(),
) {
    fun win(amount: Long?): BetResult = BetResult(bet, Winning(amount = amount ?: 0L))

    fun lose(): BetResult = BetResult(bet, Winning(amount = bet.negative()))
}
