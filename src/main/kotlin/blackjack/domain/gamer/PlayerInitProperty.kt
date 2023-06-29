package blackjack.domain.gamer

import blackjack.domain.game.Money

data class PlayerInitProperty(
    val playerName: String,
    val betAmount: Money,
)
