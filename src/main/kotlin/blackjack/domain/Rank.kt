package blackjack.domain

enum class Rank(val value: Int, val isAce: Boolean = false) {
    ACE(11, isAce = true),
    KING(10),
    QUEEN(10),
    JACK(10),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ;

    fun getValue(defaultAceValue: Int = 11): Int {
        return if (isAce) defaultAceValue else value
    }
}
