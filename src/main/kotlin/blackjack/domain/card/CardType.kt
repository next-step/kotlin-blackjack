package blackjack.domain.card

enum class CardType(
    private val expression: String,
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

    val isAce: Boolean
        get() = this == ACE

    override fun toString(): String {
        return expression
    }

    companion object {
        const val DECREMENTABLE_POINT_OF_ACE = 10
    }
}
