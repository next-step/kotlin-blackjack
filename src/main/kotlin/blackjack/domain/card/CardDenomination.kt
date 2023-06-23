package blackjack.domain.card

enum class CardDenomination {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    JACK,
    QUEEN,
    KING,
    ;

    companion object {

        val ALL_CARD_DENOMINATIONS = CardDenomination.values()
    }
}
