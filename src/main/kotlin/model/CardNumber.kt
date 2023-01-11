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

    fun isAce(): Boolean {
        return this == ACE
    }

    override fun toString(): String {
        return when (this) {
            ACE -> "A"
            JACK -> "J"
            QUEEN -> "Q"
            KING -> "K"
            TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN -> this.number.toString()
        }
    }
}
