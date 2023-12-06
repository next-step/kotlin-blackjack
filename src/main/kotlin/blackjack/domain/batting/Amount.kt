package blackjack.domain.batting

import java.math.BigDecimal

data class Amount(
    val value: BigDecimal,
) : Comparable<Amount> {

    operator fun plus(other: Amount): Amount = Amount(value.plus(other.value))
    operator fun times(count: Int): Amount = Amount(value.times(count.toBigDecimal()))

    operator fun times(count: BigDecimal): Amount =
        Amount(value * count)
    override fun compareTo(other: Amount): Int =
        this.value.compareTo(other.value)

    companion object {
        val ZERO = Amount(BigDecimal.ZERO)
        fun betAmount(amount: Int): Amount {
            require(amount > 0) { "베팅 금액은 0보다 커야 합니다" }
            return Amount(amount.toBigDecimal())
        }
    }
}
