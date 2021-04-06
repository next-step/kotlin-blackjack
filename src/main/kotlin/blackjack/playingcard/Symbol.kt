package blackjack.playingcard

enum class Symbol(val initial: String, val value: Value) {
    ACE("A", Value(11)),
    TWO("2", Value(2)),
    THREE("3", Value(3)),
    FOUR("4", Value(4)),
    FIVE("5", Value(5)),
    SIX("6", Value(6)),
    SEVEN("7", Value(7)),
    EIGHT("8", Value(8)),
    NINE("9", Value(9)),
    TEN("10", Value(10)),
    JACK("J", Value(10)),
    QUEEN("Q", Value(10)),
    KING("K", Value(10));
}
