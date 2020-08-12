package blackjack

data class Card(val type: Type, val value: Value)

enum class Type(val typeName: String) {
    SPACE("스페이스"),
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드");
}

enum class Value(val printValue: String, val value1: Int, val value2: Int) {
    ACE("A", 1, 11),
    TWO("2", 2, 2),
    THREE("3", 3, 3),
    FOUR("4", 4, 4),
    FIVE("5", 5, 5),
    SIX("6", 6, 6),
    SEVEN("7", 7, 7),
    EIGHT("8", 8, 8),
    NINE("9", 9, 9),
    TEN("10", 10, 10),
    JACK("J", 10, 10),
    QUEEN("Q", 10, 10),
    KING("K", 10, 10)
}
