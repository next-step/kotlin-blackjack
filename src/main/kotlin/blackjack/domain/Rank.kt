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
        private const val ACE_ALTERNATIVE_VALUE = 10
        private const val BLACKJACK_VALUE = 21
        private const val BLACKJACK_SIZE = 2

        fun calculateTotalValue(ranks: List<Rank>): Int {
            val nonAceValue = ranks.filterNot(Rank::isAce).sumOf(Rank::value)
            val aceCount = ranks.count(Rank::isAce)
            val aceAlternativeTotal = nonAceValue + aceCount + ACE_ALTERNATIVE_VALUE

            return if (shouldUseAceAlternativeValue(aceCount, aceAlternativeTotal)) {
                aceAlternativeTotal
            } else {
                nonAceValue + aceCount
            }
        }

        fun isBust(totalValue: Int): Boolean = totalValue > BLACKJACK_VALUE

        fun isBlackjack(
            totalValue: Int,
            size: Int,
        ): Boolean = size == BLACKJACK_SIZE && totalValue == BLACKJACK_VALUE

        private fun shouldUseAceAlternativeValue(
            aceCount: Int,
            aceAlternativeTotal: Int,
        ): Boolean = aceCount > 0 && !isBust(aceAlternativeTotal)
    }
}
