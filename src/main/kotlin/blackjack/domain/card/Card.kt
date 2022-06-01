package blackjack.domain.card

import blackjack.domain.card.type.Denomination
import blackjack.domain.card.type.Suit

data class Card(
    val denomination: Denomination,
    val suit: Suit
)
