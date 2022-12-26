package model

enum class CardNumber(val number: Int) {
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
        fun convertToString(value: CardNumber): String {
            return when (value) {
                ACE -> "A"
                JACK -> "J"
                QUEEN -> "Q"
                KING -> "K"
                else -> value.number.toString()
            }
        }
    }
}
