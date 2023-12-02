package blackjack.domain.card

data class Cards(val cards: List<Card> = listOf()) : List<Card> by cards {
    operator fun plus(other: Cards) = Cards(cards + other)
    operator fun plus(other: Card) = Cards(cards + other)
    operator fun minus(other: Card) = Cards(cards - other)
}
