package blackjack.domain

import java.math.BigDecimal

@JvmInline
value class Profit(
    val amount: BigDecimal,
) {
    companion object {
        val ZERO = Profit(BigDecimal.ZERO)
    }
}
