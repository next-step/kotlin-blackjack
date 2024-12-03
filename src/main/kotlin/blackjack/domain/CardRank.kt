package blackjack.domain

enum class CardRank(val symbol: String, private val score: Int? = null) {
    ACE("A") {
        override fun calculateScore(currentScore: Int): Int {
            val softScore = 11
            val hardScore = 1
            val maxScore = 21
            return if (currentScore + softScore > maxScore) currentScore + hardScore else currentScore + softScore
        }
    },
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    ;

    open fun calculateScore(currentScore: Int): Int {
        return currentScore + (score ?: 0)
    }

    fun isAce(): Boolean {
        return this == ACE
    }

    companion object {
        fun from(value: String): CardRank {
            return entries.find { it.symbol == value }
                ?: throw IllegalArgumentException("RankType 은 A, J, Q, K, 2~9만 가능합니다: $value")
        }
    }
}
