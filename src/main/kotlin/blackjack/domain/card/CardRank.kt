package blackjack.domain.card

enum class CardRank(
    val mark: String,
    val point: Int
) {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    ACE("A", 1),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    fun getSpecialPoint(): Int {
        if (this == ACE) {
            return 10
        }
        return 0
    }
}
