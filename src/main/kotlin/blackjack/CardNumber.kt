package blackjack

enum class CardNumber(
    val number: List<Int>,
    val symbol: String,
) {
    ACE(listOf(1, 11), "A"),
    TWO(listOf(2), "2"),
    THREE(listOf(3), "3"),
    FOUR(listOf(4), "4"),
    FIVE(listOf(5), "5"),
    SIX(listOf(6), "6"),
    SEVEN(listOf(7), "7"),
    EIGHT(listOf(8), "8"),
    NINE(listOf(9), "9"),
    TEN(listOf(10), "10"),
    KING(listOf(10), "K"),
    QUEEN(listOf(10), "Q"),
    JACK(listOf(10), "J"),
}
