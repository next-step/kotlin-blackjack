package blackjack.domain

import java.math.BigDecimal

data class Bet(
    val value: BigDecimal,
) {
    init {
        require(value > BigDecimal.ZERO) { "베팅 금액은 0보다 커야 합니다." }
    }

    constructor(value: Long) : this(value.toBigDecimal())
}
