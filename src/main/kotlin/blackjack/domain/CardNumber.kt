package blackjack.domain

enum class CardNumber(private val score: Int) {
    ACE(1) {
        override fun appendScore(totalSum: Int): Int {
            return if (totalSum + 11 <= 21) 11 else 1
        }
    },
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
    KING(10), ;

    open fun appendScore(totalSum: Int): Int = score
}
