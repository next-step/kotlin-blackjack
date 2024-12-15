package blackjack.entity

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol

data class CardInfo(
    val card: Map<CardSymbol, Card>,
    val isFaceUp: Boolean,
)
