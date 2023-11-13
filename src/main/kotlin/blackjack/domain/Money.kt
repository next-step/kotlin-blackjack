package blackjack.domain

@JvmInline
value class Money(val amount: Int = 0) {
    fun calculateWinLoseMoney(win: WinLose, isBlackjack: Boolean = false): Money {
        return when (win) {
            WinLose.WIN -> calculateWinMoney(isBlackjack)
            WinLose.LOSE -> Money(-amount)
            WinLose.DRAW -> Money(0)
            WinLose.NONE -> Money(0)
        }
    }

    private fun calculateWinMoney(isBlackjack: Boolean): Money {
        if (isBlackjack) {
            return Money((amount * 1.5).toInt())
        }
        return Money(amount)
    }
}
