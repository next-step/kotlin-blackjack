package blackjack.domain

enum class CardType {
    SPACE,
    CLOVER,
    HEART,
    DIAMOND;
}

enum class CardValue(value: Int) {
    A(100),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10);
}
