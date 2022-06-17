package blackjack.domain

data class Card(
    val symbol: Symbol,
    val number: CardNumber
) {
    override fun toString() = number.displayStr + symbol.displayStr
}

enum class Symbol(val displayStr: String) {
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드")
}

enum class CardNumber(val value: Int, val displayStr: String, val anotherValue: Int? = null) {
    ACE(1, "A", 11),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");
}
