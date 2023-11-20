package blackjack.domain

import blackjack.entity.CardNumber
import blackjack.entity.CardShape

data class Card(
    val number: CardNumber,
    val shape: CardShape
)
