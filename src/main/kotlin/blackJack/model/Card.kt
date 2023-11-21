package blackJack.model

import blackJack.model.enums.Rank
import blackJack.model.enums.Suit

data class Card(
    val suit: Suit,
    val rank: Rank
)
