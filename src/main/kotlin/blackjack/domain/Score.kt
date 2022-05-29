package blackjack.domain

@JvmInline
value class Score(val cards: List<Card>) {

    val isBlackjack: Boolean
        get() = true

    val isBust: Boolean
        get() = true
}
