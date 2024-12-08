package blackjack.domain

enum class Rank(
    val value: Int,
    val tier: String,
) {
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
    KING(10, "K"),
    ;

    fun isAce(): Boolean = this == ACE

    companion object {
        private const val ACE_ALTERNATIVE_VALUE = 11

        fun calculateTotalValue(ranks: List<Rank>): Int {
            val nonAceValue = ranks.filterNot(Rank::isAce).sumOf(Rank::value)
            val aceCount = ranks.count(Rank::isAce)

            return if (checkAceValue(aceCount, nonAceValue)) {
                nonAceValue + ACE_ALTERNATIVE_VALUE + (aceCount - 1)
            } else {
                nonAceValue + aceCount
            }
        }

        private fun checkAceValue(
            aceCount: Int,
            nonAceValue: Int,
        ): Boolean = checkAceCount(aceCount) && checkNotBurst(nonAceValue, aceCount)

        private fun checkAceCount(aceCount: Int): Boolean = aceCount > 0

        private fun checkNotBurst(
            nonAceValue: Int,
            aceCount: Int,
        ): Boolean = !GameStatus.isBurst(nonAceValue + ACE_ALTERNATIVE_VALUE + (aceCount - 1))
    }
}
