package blackjack.domain.bet

@JvmInline
value class Bet(val amount: Double = DEFAULT_AMOUNT) {

    companion object {
        private const val DEFAULT_AMOUNT: Double = 0.0
    }
}
