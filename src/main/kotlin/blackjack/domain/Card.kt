package blackjack.domain

import blackjack.model.CardShape
import blackjack.model.CardType

val DEFAULT_CARD_DECK: MutableList<Card> = CardType.values()
    .flatMap { type -> CardShape.values().map { shape -> Card(type = type, shape = shape) } }
    .toMutableList()

class Card(val type: CardType, val shape: CardShape)
