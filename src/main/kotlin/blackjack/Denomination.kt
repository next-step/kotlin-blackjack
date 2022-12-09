package blackjack

const val ACE_MIN_SCORE = 1
const val ACE_MAX_SCORE = 11

enum class Denomination(val value: String, val score: Int) {
    ACE("A", ACE_MAX_SCORE),
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
    KING("K", 10),
    QUEEN("Q", 10)
}
