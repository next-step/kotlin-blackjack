package blackjack.step2.domain

enum class CardNumber(val scores: List<Int>) {
    ACE(listOf(1, 11)),
    TWO(listOf(2)),
    THREE(listOf(3)),
    FOUR(listOf(4)),
    FIVE(listOf(5)),
    SIX(listOf(6)),
    SEVEN(listOf(7)),
    EIGHT(listOf(8)),
    NINE(listOf(9)),
    TEN(listOf(10)),
    KING(listOf(10)),
    QUEEN(listOf(10)),
    JACK(listOf(10)),
}
