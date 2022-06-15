package blackjack.domain.common

import java.math.BigDecimal

@JvmInline
value class Money private constructor(val amount: BigDecimal) : Comparable<Money> {

    init {
        require(amount.scale() == 0) {
            "금액의 최소 단위는 1원입니다"
        }
    }

    override fun compareTo(other: Money): Int {
        return amount.compareTo(other.amount)
    }

    operator fun plus(other: Money): Money {
        return Money(amount + other.amount)
    }

    operator fun minus(other: Money): Money {
        return Money(amount - other.amount)
    }

    operator fun unaryMinus(): Money {
        return Money(-amount)
    }

    fun multiply(rate: Double): Money {
        return Money(amount.multiply(rate.toBigDecimal()).setScale(0))
    }

    companion object {
        val ZERO: Money = Money(BigDecimal.ZERO)

        fun of(amount: Int): Money {
            return Money(amount.toBigDecimal())
        }
    }
}
