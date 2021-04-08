package blackjack.domain

import java.math.BigDecimal

class Profits(
    val dealerProfit: Profit,
    val playerProfits: List<Profit>
)

class Profit(
    val name: String,
    val amount: BigDecimal
)
