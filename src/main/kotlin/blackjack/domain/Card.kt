package blackjack.domain

const val ACE_SOFT = 10
const val BLACKJACK = 21

data class Card(val cardSuit: CardSuit, val cardNumber: CardNumber)

enum class CardSuit(val displayName: String) {
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLUB("클로버")
}

enum class CardNumber(val value: Int, val displayName: String) {

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
}


