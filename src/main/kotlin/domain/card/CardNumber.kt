package domain.card

enum class CardNumber(val score: Int) {
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
    KING(10),
    QUEEN(10),
    JACK(10),
    ;

    fun isAce(): Boolean = when (this) {
        ACE -> true
        else -> false
    }

    fun isTenScore(): Boolean = when (this) {
        TEN, KING, QUEEN, JACK -> true
        else -> false
    }
}
