package blackjack

enum class CardNumber(
    private val mark: String,
    private val score: Int,
    private val otherScore: Int,
) {

    ONE("1", 1, 1),
    TWO("2", 2, 2),
    THREE("3", 3, 3),
    FOUR("4", 4, 4),
    FIVE("5", 5, 5),
    SIX("6", 6, 6),
    SEVEN("7", 7, 7),
    EIGHT("8", 8, 8),
    NINE("9", 9, 9),
    TEN("10", 10, 10),
    KING("K", 10, 10),
    QUEEN("Q", 10, 10),
    JACK("J", 10, 10),
    ACE("A", 1, 11),
}
