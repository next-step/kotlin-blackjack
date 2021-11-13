package blackjack.domain

/**
 * 끝수.
 */
enum class Denomination(val mark: String, val point: Pair<Int, Int?>) {
    ACE("A", Pair(1, 11)),
    TWO("2", Pair(2, null)),
    THREE("3", Pair(3, null)),
    FOUR("4", Pair(4, null)),
    FIVE("5", Pair(5, null)),
    SIX("6", Pair(6, null)),
    SEVEN("7", Pair(7, null)),
    EIGHT("8", Pair(8, null)),
    NINE("9", Pair(9, null)),
    TEN("10", Pair(10, null)),
    JACK("J", Pair(10, null)),
    QUEEN("Q", Pair(10, null)),
    KING("K", Pair(10, null))
}
