package blackjack.domain.card

enum class CardNumber(val cardScore: CardScore) {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11, 1);

    constructor(number: Int) : this(CardScore(number))
    constructor(primaryNumber: Int, secondaryNumber: Int) : this(CardScore(primaryNumber, secondaryNumber))
}
