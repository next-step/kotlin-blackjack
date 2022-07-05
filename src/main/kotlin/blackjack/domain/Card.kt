package blackjack.domain

data class Card(
    val symbol: Symbol,
    val number: CardNumber
) {
    val numberValue: Int = number.value

    val isAce: Boolean = number == CardNumber.ACE

    fun getAceNumberValue(sumValue: Int): Int {
        if (sumValue <= MAX_POINT - ACE_ANOTHER_VALUE)
            return ACE_ANOTHER_VALUE

        return CardNumber.ACE.value
    }

    companion object {
        private const val MAX_POINT = 21
        private const val ACE_ANOTHER_VALUE = 11
    }
}

enum class Symbol(val displayStr: String) {
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드")
}

enum class CardNumber(val value: Int, val displayStr: String) {
    ACE(1, "A"),
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
