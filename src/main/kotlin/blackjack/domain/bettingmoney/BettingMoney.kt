package blackjack.domain.bettingmoney

import java.math.BigDecimal

@JvmInline
value class BettingMoney(
    val value: BigDecimal = BigDecimal.ZERO
) {
    constructor(value: Long) : this(BigDecimal.valueOf(value))

    init {
        require(value >= BigDecimal.ZERO) { "배팅 금액은 양수만 입력할 수 있습니다. value = $value" }
    }
}
