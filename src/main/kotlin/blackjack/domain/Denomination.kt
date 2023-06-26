package blackjack.domain

enum class Denomination(val score: Int) {
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
    KING(10);

    companion object {
        private const val LOWER_ACE = 1
        private const val HIGHER_ACE = 11
        private const val ACE_THRESHOLD = 10

        fun from(denomination: Denomination, sum: Int) =
            if (denomination.isAce()) {
                getAce(sum)
            } else {
                denomination.score
            }

        private fun getAce(sum: Int) = if (sum < ACE_THRESHOLD) HIGHER_ACE else LOWER_ACE
    }
}

fun Denomination.isAce() = this == Denomination.ACE
