package blackjack.vo

import blackjack.domain.Player

data class GameProfitResult(
    val dealerProfit: Int,
    val playersProfits: Map<Player, Int>,
)
