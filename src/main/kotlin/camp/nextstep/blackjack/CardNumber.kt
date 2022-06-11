package camp.nextstep.blackjack

enum class CardNumber {
    ACE,
    TWO, THREE, FOUR, FIVE,
    SIX, SEVEN, EIGHT, NINE,
    TEN, JACK, QUEEN, KING;

    companion object {
        const val CARD_NUMBERS = 13
    }
}
