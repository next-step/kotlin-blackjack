package blackjack.vo

import blackjack.domain.Player

data class BettingResultVO(
    val dealerProfit: Int,
    val playersProfits: Map<Player, Int>,
)
