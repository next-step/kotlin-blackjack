package blackjack.model

val DEFAULT_CARD_DECK: MutableList<Card> = CardType.values()
    .flatMap { type -> CardShape.values().map { shape -> Card(type = type, shape = shape) } }
    .toMutableList()

data class Card(val type: CardType, val shape: CardShape)
