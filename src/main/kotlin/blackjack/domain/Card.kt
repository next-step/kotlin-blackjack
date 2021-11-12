package blackjack.domain

data class Card(
    val pattern: String,
    val denomination: String,
    val value: List<Int>,
)
