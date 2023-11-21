package blackjack.domain

enum class Rank(val value: Int, val nameValue: String) {
    Ace(1, "1"),
    Two(2, "2"),
    Three(3, "3"),
    Four(4, "4"),
    Five(5, "5"),
    Six(6, "6"),
    Seven(7, "7"),
    Eight(8, "8"),
    Nine(9, "9"),
    Ten(10, "10"),
    Jack(10, "J"),
    Queen(10, "Q"),
    King(10, "K"),
}
