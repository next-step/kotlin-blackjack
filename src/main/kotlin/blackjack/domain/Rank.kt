package blackjack.domain

enum class Rank(val score: Int, val rankName: String) {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    companion object {
        fun of(rank: String): Rank {
            return values().find { it.findRank(rank) } ?: throw IllegalArgumentException("존재하지 않는 숫자입니다.")
        }

        private fun Rank.findRank(rank: String): Boolean {
            return name == rank
        }
    }
}
