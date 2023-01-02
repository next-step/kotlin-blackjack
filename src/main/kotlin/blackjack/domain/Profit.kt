package blackjack.domain

@JvmInline
value class Profit(val value: Int = DEFAULT) {
    operator fun unaryMinus(): Profit =
        Profit(-this.value)

    operator fun plus(profit: Profit): Int =
        this.value + profit.value

    companion object {
        private const val DEFAULT = 0
        val ZERO = Profit(0)
    }
}
