package blackjack.domain

enum class CardSpell(
    val digit: Int,
    val minorDigit: Int?,
    private val alias: String?
) {
    ACE(1, 11, "A"),
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10, "J"), QUEEN(10, "Q"), KING(10, "K");

    constructor(digit: Int) : this(digit, null, null)
    constructor(digit: Int, alias: String) : this(digit, null, alias)

    override fun toString(): String {
        if (alias != null) return alias

        return digit.toString()
    }
}
