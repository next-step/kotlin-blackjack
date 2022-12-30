package blackjack.domain.value

private const val BLACK_JACK_WINNING_AMOUNT_RATE = 1.5
private const val NO_AMOUNT = 0

@JvmInline
value class BettingAmount(private val amount: Int) {
    fun win() = amount
    fun blackJack() = (amount * BLACK_JACK_WINNING_AMOUNT_RATE).toInt()
    fun lose() = -amount
    fun tie() = NO_AMOUNT
}
