package blackjack.domain.card

import blackjack.domain.card.type.Denomination
import blackjack.domain.card.type.Suit

data class Card(
    private val denomination: Denomination,
    private val suit: Suit
)
