package blackjack

class Card private constructor(val type: CardType, val value: CardValue) {
    companion object {
        private val TYPES = listOf(CardType.SPADE, CardType.CLOVER, CardType.DIAMOND, CardType.HEART)

        val VALUES = listOf(
            CardValue.TWO,
            CardValue.THREE,
            CardValue.FOUR,
            CardValue.FIVE,
            CardValue.SIX,
            CardValue.SEVEN,
            CardValue.EIGHT,
            CardValue.NINE,
            CardValue.TEN,
            CardValue.JACK,
            CardValue.QUEEN,
            CardValue.KING,
            CardValue.ACE
        )

        private val CARDS: Map<Pair<CardType, CardValue>, Card> = TYPES.flatMap { type ->
            VALUES.map { Pair(type, it) }
        }.associateWith { Card(it.first, it.second) }

        fun from(type: CardType, value: CardValue): Card {
            return CARDS[Pair(type, value)] ?: throw IllegalArgumentException()
        }
    }
}

enum class CardType {
    SPADE,
    CLOVER,
    DIAMOND,
    HEART,
}

enum class CardValue {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
}
