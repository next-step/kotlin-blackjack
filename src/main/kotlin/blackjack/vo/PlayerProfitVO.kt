package blackjack.vo

import blackjack.domain.Profit
import java.math.BigDecimal

class PlayerProfitVO(
    val name: String,
    val profitAmount: BigDecimal,
) {
    companion object {
        fun of(name: String, profit: Profit) = PlayerProfitVO(
            name = name,
            profitAmount = profit.amount,
        )
    }
}
