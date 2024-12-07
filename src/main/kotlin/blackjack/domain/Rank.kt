package blackjack.domain

enum class Rank(val value: Int) {
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
    JACK(10),
    QUEEN(10),
    KING(10),
    ;

    fun isAce(): Boolean = this == ACE

    companion object {
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
        ): Boolean = checkAceCount(aceCount) && checkBurst(nonAceValue, aceCount)

        private fun checkAceCount(aceCount: Int): Boolean = aceCount > 0

        private fun checkBurst(
            nonAceValue: Int,
            aceCount: Int,
        ): Boolean = nonAceValue + ACE_ALTERNATIVE_VALUE + (aceCount - 1) <= BLACKJACK_VALUE
    }
}
