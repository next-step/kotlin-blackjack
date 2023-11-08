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
    JACK(10),
    QUEEN(10),
    KING(10);

    companion object {
        fun of(rank: String): Rank {
            return values().find { it.findRank(rank) } ?: throw IllegalArgumentException("존재하지 않는 숫자입니다.")
        }

        private fun Rank.findRank(rank: String): Boolean {
            return name == rank
        }
    }
}
