package domain.card

enum class Denomination(val numbers: Set<Int>) {
    ACE(setOf(1, 11)),
    TWO(setOf(2)),
    THREE(setOf(3)),
    FOUR(setOf(4)),
    FIVE(setOf(5)),
    SIX(setOf(6)),
    SEVEN(setOf(7)),
    EIGHT(setOf(8)),
    NINE(setOf(9)),
    TEN(setOf(10)),
    KING(setOf(10)),
    QUEEN(setOf(10)),
    JACK(setOf(10));
}
