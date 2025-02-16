package blackjack.domain

import java.math.BigDecimal

data class DealerResult(
    val dealer: Dealer,
    val profit: BigDecimal,
)
