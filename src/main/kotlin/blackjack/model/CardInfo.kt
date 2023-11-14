package blackjack.model

enum class CardInfo(
    val displayName: String,
    val value1: Int,
    val value2: Int = value1,
) {
    Ace("A", 1, 11),
    One("1", 1),
    Two("2", 2),
    Three("3", 3),
    Four("4", 4),
    Five("5", 5),
    Six("6", 6),
    Seven("7", 7),
    Eight("8", 8),
    Nine("9", 9),
    King("K", 10),
    Queen("Q", 10),
    Jack("J", 10),
}
