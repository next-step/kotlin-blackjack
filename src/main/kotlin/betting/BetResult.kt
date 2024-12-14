package betting

sealed class BetResult(
    open val bet: Bet,
    val winning: Winning = Winning(),
) {
    data class Default(
        override val bet: Bet = Bet(),
    ) : BetResult(bet = bet)

    data class Win(
        override val bet: Bet,
        val amount: Double?,
    ) : BetResult(
            bet = bet,
            winning = Winning(amount = amount ?: 0.0),
        )

    data class Lose(
        override val bet: Bet,
        val amount: Double?,
    ) : BetResult(
            bet = bet,
            winning = Winning(amount = amount ?: 0.0),
        )
}
