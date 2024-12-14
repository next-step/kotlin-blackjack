package blackjack.domain

@JvmInline
value class BettingMoney(val amount: Int) {
    fun profitForWin(player: Player): Int {
        if (player.isBlackjack()) {
            return (amount * 1.5).toInt()
        }
        return amount
    }

    fun profitForLose(): Int = -amount
}
