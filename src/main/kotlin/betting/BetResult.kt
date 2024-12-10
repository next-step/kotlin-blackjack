package betting

data class BetResult (
    val bet: Bet,
    val winning: Winning = Winning(),
) {
    fun win(amount: Double?): BetResult = BetResult(bet, Winning(amount = amount ?: 0.0))

    fun lose(): BetResult = BetResult(bet, Winning(amount = bet.negative()))

    fun lose(amount: Double?): BetResult = BetResult(bet, Winning(amount = this.winning.amount + (amount ?: 0.0)))
}
