package blackjack.enums

enum class CardType(
    val expression: String,
    val point: Int
) {
    Two("2", 2),
    Three("3", 3),
    Four("4", 4),
    Five("5", 5),
    Six("6", 6),
    Seven("7", 7),
    Eight("8", 8),
    Nine("9", 9),
    Ten("10", 10),
    King("K", 10),
    Queen("Q", 10),
    Jack("J", 10),
    Ace("A", 11);

    companion object {
        const val DECREMENTABLE_POINT_OF_ACE = 10
    }
}
