package blackjack.domain

data class Score(
    val value: Int,
    val bust: Boolean = false
)
