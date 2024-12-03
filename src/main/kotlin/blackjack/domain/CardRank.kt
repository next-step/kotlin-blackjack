package blackjack.domain

enum class CardRank(val symbol: String, private val score: Int? = null) {
    ACE("A") {
        override fun calculateScore(currentScore: Int): Int {
            return if (currentScore + ACE_SOFT_SCORE > BUST_SCORE) currentScore + ACE_HARD_SCORE else currentScore + ACE_SOFT_SCORE
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

    companion object {
        const val ACE_SOFT_SCORE = 11
        const val ACE_HARD_SCORE = 1

        fun from(value: String): CardRank {
            return entries.find { it.symbol == value }
                ?: throw IllegalArgumentException("RankType 은 ${symbols()}만 가능합니다: $value")
        }

        fun symbols(): List<String> {
            return entries.map { it.symbol }.toList()
        }
    }
}
