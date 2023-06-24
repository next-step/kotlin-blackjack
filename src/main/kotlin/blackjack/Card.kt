package blackjack

class Card private constructor(val type: CardType, val value: CardValue) {
    companion object {
        private val CARDS: Map<Pair<CardType, CardValue>, Card> = CardType.values().flatMap { type ->
            CardValue.values().map { Pair(type, it) }
        }.associateWith { Card(it.first, it.second) }

        fun from(type: CardType, value: CardValue): Card {
            return CARDS[Pair(type, value)] ?: throw IllegalArgumentException()
        }
    }
}
