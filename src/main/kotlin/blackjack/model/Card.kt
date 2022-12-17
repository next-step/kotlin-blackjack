package blackjack.model

val DEFAULT_CARD_DECK: List<Card> = CardType.values()
    .flatMap { type -> CardShape.values().map { shape -> Card(type = type, shape = shape) } }

data class Card(val type: CardType, val shape: CardShape)
