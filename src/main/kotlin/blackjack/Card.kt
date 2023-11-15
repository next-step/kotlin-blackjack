package blackjack

data class Card(
    val symbol: Symbol,
    val number: Number
) {
    enum class Number(
        val value: Int,
        val other: Int = 0
    ){
        ACE(1, 11),
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
        KING(10)
    }

    enum class Symbol(
        val kor: String
    ) {
        DIAMOND("다이아몬드"),
        SPADE("스페이드"),
        CLOVER("클로버"),
        HEART("하트")
    }
}
