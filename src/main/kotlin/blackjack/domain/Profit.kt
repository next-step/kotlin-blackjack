package blackjack.domain

@JvmInline
value class Profit(val value: Int = DEFAULT) {

    companion object {
        private const val DEFAULT = 0
        val ZERO = Profit(0)
    }
}
