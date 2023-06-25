package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Symbol

data class Card(
    val rank: Rank,
    val symbol: Symbol
)
