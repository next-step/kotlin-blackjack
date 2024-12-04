package blackjack.domain

enum class CardRank(private val score: Int? = null) {
    ACE {
        override fun calculateScore(currentScore: Int): Int {
            return if (currentScore + ACE_SOFT_SCORE > BUST_SCORE) currentScore + ACE_HARD_SCORE else currentScore + ACE_SOFT_SCORE
        }
    },
    JACK(10),
    QUEEN(10),
    KING(10),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    ;

    open fun calculateScore(currentScore: Int): Int {
        return currentScore + (score ?: 0)
    }

    companion object {
        const val ACE_SOFT_SCORE = 11
        const val ACE_HARD_SCORE = 1
    }
}
