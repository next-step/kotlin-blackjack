package blackjack

enum class States(val state: Int) {
    HIT(1),
    BUST(2),
    BLACK_JACK(3),
    STAY(4),
    UNDEFINED(5);
}
