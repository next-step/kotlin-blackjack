package blackjack.enums

enum class CardType(
    val description: String,
    val point: Int
) {
    Two("하트", 2),
    Three("하트", 3),
    Four("하트", 4),
    Five("하트", 5),
    Six("하트", 6),
    Seven("하트", 7),
    Eight("하트", 8),
    Nine("하트", 9),
    Ten("하트", 10),
    King("하트", 10),
    Queen("하트", 10),
    Jack("하트", 10),
    Ace("하트", 11);
}
