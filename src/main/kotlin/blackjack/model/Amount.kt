package blackjack.model

import kotlin.math.roundToInt

@JvmInline
value class Amount(private val value: Double) {

    fun profit(earningRate: EarningRate): Profit = Profit(this * earningRate)

    fun roundToInt(): Int = value.roundToInt()

    operator fun unaryMinus(): Amount = Amount(value.unaryMinus())

    operator fun times(earningRate: EarningRate): Amount = Amount(value * earningRate.value)

    override fun toString(): String = value.toString()

    companion object {
        val ZERO = Amount(0.0)
    }
}
