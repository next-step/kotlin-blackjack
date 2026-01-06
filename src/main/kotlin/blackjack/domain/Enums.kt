package blackjack.domain

enum class Suit(val displayName: String) {
    CLUB("클로버"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드"),
}

enum class Rank(val displayName: String, val score: Int) {
    ACE("A", 1),
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
    KING("K", 10),
}

enum class RecordType(val payoutRate: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
}
