package blackjack.domain

enum class Ranks(
    val keyword: String,
    val points: List<Int>,
) {
    ACE("A", listOf(1, 11)),
    TWO("2", listOf(2)),
    THREE("3", listOf(3)),
    FOUR("4", listOf(4)),
    FIVE("5", listOf(5)),
    SIX("6", listOf(6)),
    SEVEN("7", listOf(7)),
    EIGHT("8", listOf(8)),
    NINE("9", listOf(9)),
    TEN("10", listOf(10)),
    JACK("J", listOf(10)),
    QUEEN("Q", listOf(10)),
    KING("K", listOf(10)),
}
