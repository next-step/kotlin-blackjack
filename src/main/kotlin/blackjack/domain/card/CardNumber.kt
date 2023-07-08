package blackjack.domain.card

enum class CardNumber(val score: Int) {
    CARD_TWO(2),
    CARD_THREE(3),
    CARD_FOUR(4),
    CARD_FIVE(5),
    CARD_SIX(6),
    CARD_SEVEN(7),
    CARD_EIGHT(8),
    CARD_NINE(9),
    CARD_TEN(10),
    CARD_JACK(10),
    CARD_QUEEN(10),
    CARD_KING(10),
    CARD_ACE(1)
}
