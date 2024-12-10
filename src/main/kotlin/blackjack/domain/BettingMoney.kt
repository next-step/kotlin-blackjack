package blackjack.domain

@JvmInline
value class BettingMoney(val amount: Int) {
    fun profitForWin(): Int = amount

    fun profitForBlackjackWin(): Int = (amount * 1.5).toInt()

    fun profitForLose(): Int = -amount
}
