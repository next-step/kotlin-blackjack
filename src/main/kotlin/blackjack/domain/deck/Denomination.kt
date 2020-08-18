package blackjack.domain.deck

enum class Denomination(private val number: Int, val value: String) {
    ACE(1, "A"),
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
    KING(10, "K");

    fun calculate(value: Int = 0): Int {
        return if (this == ACE) {
            if (11 + value > 21) 1
            else 11
        } else number
    }
}
