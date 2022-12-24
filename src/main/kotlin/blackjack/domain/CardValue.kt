package blackjack.domain

enum class CardValue(val value: Int) {
    ACE(1),
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
    TEN(10);

    companion object {
        private const val ACE_BONUS_VALUE = 10
        fun sum(cardValues: List<CardValue>): Int {
            val hasAce = cardValues.contains(ACE)

            if (!hasAce) {
                return cardValues.sumOf { it.value }
            }

            return cardValues.minus(ACE)
                .sumOf { it.value }
                .let {
                    addAceValue(it)
                }
        }

        private fun addAceValue(sum: Int) = if (canAddAceBonusValue(sum)) {
            sum + ACE.value + ACE_BONUS_VALUE
        } else {
            sum + ACE.value
        }

        private fun canAddAceBonusValue(sum: Int) = sum <= 10
    }
}
