package blackjack.domain


enum class Rank(val score: Int) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10);

    companion object {
        private val RANK_SET = values().associateBy { it.score }

        fun getRankSet(): Map<Int, Rank> {
            return RANK_SET
        }
    }
}
