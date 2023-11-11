package blackjack.domain

data class Player(
    val name: String,
    val cards: Cards = Cards(emptyList()),
)
