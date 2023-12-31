package blackjack.domain.batting

import java.math.BigDecimal

data class Amount(
    val value: BigDecimal,
) : Comparable<Amount> {

    init {
        require(value >= BigDecimal.ZERO) { "금액은 0이상이어야 합니다" }
    }

    operator fun plus(other: Amount): Amount = Amount(value.plus(other.value))
    operator fun times(count: Int): Amount = Amount(value.times(count.toBigDecimal()))

    operator fun times(count: BigDecimal): Amount =
        Amount(value * count)
    override fun compareTo(other: Amount): Int =
        this.value.compareTo(other.value)

    companion object {
        val ZERO = Amount(BigDecimal.ZERO)
    }
}
