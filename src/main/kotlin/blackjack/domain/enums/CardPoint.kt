package blackjack.domain.enums

enum class CardPoint(val score: Int) {
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
        const val BLACK_JACK_SCORE = 21
        const val ACE_SPECIAL_VALUE = 11
    }
}
