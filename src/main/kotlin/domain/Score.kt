package domain

object Score {
    const val BLACKJACK = 21
    const val DEALER_HIT_ON_MAX = 16

    fun isBust(score: Int): Boolean {
        return score > BLACKJACK
    }

    fun isBlackJack(score: Int): Boolean {
        return score == BLACKJACK
    }

    fun isDealerStayOn(score: Int): Boolean {
        return score in (DEALER_HIT_ON_MAX + 1) until BLACKJACK
    }
}
