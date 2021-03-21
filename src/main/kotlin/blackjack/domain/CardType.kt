package blackjack.domain

enum class CardType(
    val expression: String,
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
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10),
    ACE("A", 11);

    companion object {
        fun findAceCount(cards: Set<Card>): Int {
            return cards.count { it.type == ACE }
        }

        const val DECREMENTABLE_POINT_OF_ACE = 10
    }
}
