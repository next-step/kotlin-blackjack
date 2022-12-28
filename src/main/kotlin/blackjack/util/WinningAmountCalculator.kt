package blackjack.util

private const val BLACK_JACK_WINNING_AMOUNT_RATE = 1.5
private const val NO_AMOUNT = 0

data class WinningAmountCalculator(val winningAmount: Int) {
    companion object {
        fun win(amount: Int) = WinningAmountCalculator(amount)
        fun blackJack(amount: Int) =
            WinningAmountCalculator((amount * BLACK_JACK_WINNING_AMOUNT_RATE).toInt())

        fun lose(amount: Int) = WinningAmountCalculator(-amount)
        fun tie() = WinningAmountCalculator(NO_AMOUNT)
    }
}
