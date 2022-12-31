package blackjack.domain

@JvmInline
value class Profit(val value: Int = DEFAULT_PROFIT) {
    companion object {
        private const val DEFAULT_PROFIT = 0
    }
}
