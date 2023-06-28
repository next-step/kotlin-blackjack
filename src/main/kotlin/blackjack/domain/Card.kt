package blackjack.domain

const val ACE_SOFT = 10
const val BLACKJACK = 21

data class Card(val cardShape: CardShape, val cardNumber: CardNumber)
enum class CardShape(val displayName: String) {
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLUB("클로버")
}
enum class CardNumber(val value: Int, val displayName: String) {
    ACE(1, "A"), _2(2, "2"), _3(3, "3"),
    _4(4, "4"), _5(5, "5"), _6(6, "6"),
    _7(7, "7"), _8(8, "8"), _9(9, "9"),
    _10(10, "10"), JACK(10, "J"), QUEEN(10, "Q"),
    KING(10, "K");
}
