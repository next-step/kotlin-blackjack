package blackjack.domain

object Rule {
    private const val BLACKJACK_VALUE = 21

    fun isBurst(score: Int): Boolean = score > BLACKJACK_VALUE

    fun isBlackjack(score: Int): Boolean = score == BLACKJACK_VALUE
}
