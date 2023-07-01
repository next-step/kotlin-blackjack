package blackjack.domain.gamer

import blackjack.domain.money.Money

data class PlayerInitProperty(
    val playerName: String,
    val betAmount: Money,
)
