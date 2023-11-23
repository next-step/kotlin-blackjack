package blackjack

data class Card(
    val symbol: Symbol,
    val number: Number
) {
    enum class Number(
        val print: String,
        val value: Int,
        val other: Int = 0
    ) {
        ACE("A", 11, 1),
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("J", 10),
        QUEEN("Q", 10),
        KING("K", 10)
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
