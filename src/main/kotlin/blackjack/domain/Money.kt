package blackjack.domain

@JvmInline
value class Money(val amount: Int = 0) {

    operator fun plus(money: Money): Money {
        return Money(amount + money.amount)
    }

    fun calculateWinLoseMoney(win: WinLose, isBlackjack: Boolean = false): Money {
        return when (win) {
            WinLose.WIN -> calculateWinMoney(isBlackjack)
            WinLose.LOSE -> opposite()
            WinLose.DRAW -> Money(0)
            WinLose.NONE -> Money(0)
        }
    }

    fun opposite(): Money {
        return Money(-amount)
    }

    private fun calculateWinMoney(isBlackjack: Boolean): Money {
        if (isBlackjack) {
            return Money((amount * 1.5).toInt())
        }
        return Money(amount)
    }
}
