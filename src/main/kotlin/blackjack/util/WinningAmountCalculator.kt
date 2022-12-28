package blackjack.util

import blackjack.domain.GameResultTitle

private const val BLACK_JACK_WINNING_AMOUNT_RATE = 1.5
private const val NO_AMOUNT = 0

data class WinningAmountCalculator(val gameResultTitle: GameResultTitle, val winningAmount: Int) {
    companion object {
        fun win(amount: Int) = WinningAmountCalculator(GameResultTitle.WIN, amount)
        fun blackJack(amount: Int) =
            WinningAmountCalculator(GameResultTitle.BLACK_JACK, (amount * BLACK_JACK_WINNING_AMOUNT_RATE).toInt())

        fun lose(amount: Int) = WinningAmountCalculator(GameResultTitle.LOSE, -amount)
        fun tie() = WinningAmountCalculator(GameResultTitle.TIE, NO_AMOUNT)
    }
}
