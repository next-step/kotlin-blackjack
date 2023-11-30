package blackjack.domain

enum class Denomination(
    val symbol: String,
    val score: Int,
    val optionScore: Int = 0
) {
    ACE("1", 1, 10),
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
    KING("K", 10);

}
