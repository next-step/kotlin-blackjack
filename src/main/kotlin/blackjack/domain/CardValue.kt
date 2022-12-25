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
        const val ACE_BONUS_VALUE = 10
    }
}
