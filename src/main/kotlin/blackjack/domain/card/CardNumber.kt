package blackjack.domain.card

enum class CardNumber(
    val number: Int,
    val description: String,
    val candidateNumber: Int = number
) {

    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ACE(11, "A", 1);

    val candidateNumbers: Set<Int>
        get() = setOf(number, candidateNumber)

    companion object {
        val INT_RANGE: IntRange = (ONE.number..ACE.number)
    }
}
