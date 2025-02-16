package blackjack.domain

import java.math.BigDecimal

data class GamblerResult(
    val gambler: Gambler,
    val profit: BigDecimal,
)
