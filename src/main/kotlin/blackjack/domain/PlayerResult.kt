package blackjack.domain

import java.math.BigDecimal

data class PlayerResult(
    val name: String,
    val profit: BigDecimal,
)
