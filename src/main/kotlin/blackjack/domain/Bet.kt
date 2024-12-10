package blackjack.domain

import java.math.BigDecimal

@JvmInline
value class Bet(
    val value: BigDecimal,
) {
    init {
        require(value > BigDecimal.ZERO) { "베팅 금액은 0보다 커야 합니다." }
    }

    constructor(value: Long) : this(value.toBigDecimal())

    constructor(value: Double) : this(value.toBigDecimal())

    operator fun plus(other: Bet): Bet = Bet(value + other.value)

    operator fun minus(other: Bet): Bet = Bet(value - other.value)

    operator fun times(multiplier: Double): Bet = Bet(value * multiplier.toBigDecimal())
}
