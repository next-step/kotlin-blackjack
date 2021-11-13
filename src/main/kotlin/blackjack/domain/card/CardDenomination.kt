package blackjack.domain.card

enum class CardDenomination(
    val denomination: String,
    val value: List<Int>,
    val order: Int,
) {
    ACE("ACE", listOf(1, 11), 1),
    TWO("2", listOf(2), 2),
    THREE("3", listOf(3), 3),
    FOUR("4", listOf(4), 4),
    FIVE("5", listOf(5), 5),
    SIX("6", listOf(6), 6),
    SEVEN("7", listOf(7), 7),
    EIGHT("8", listOf(8), 8),
    NINE("9", listOf(9), 9),
    TEN("10", listOf(10), 10),
    JACK("J", listOf(10), 11),
    QUEEN("Q", listOf(10), 12),
    KING("K", listOf(10), 13),
}
