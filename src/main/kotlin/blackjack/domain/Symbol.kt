package blackjack.domain

enum class Symbol(val value: Int) {
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
        const val ACE_BONUS_VALUE = 10
        const val ACE_BONUS_VALUE_THRESHOLD = 11
    }
}
