package blackjack.domain.batting

import java.math.BigDecimal

@JvmInline
value class BetAmount(
    val value: Amount,
) {
    init {
        require(value > Amount.ZERO) { "베팅 금액은 0보다 커야 합니다" }
    }

    constructor(amount: BigDecimal) : this(Amount(amount))
}
