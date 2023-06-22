package blackjack.domain

enum class CardNumber(val number: String, val score: Int) {
    CARD_TWO("2", 2),
    CARD_THREE("3", 3),
    CARD_FOUR("4", 4),
    CARD_FIVE("5", 5),
    CARD_SIX("6", 6),
    CARD_SEVEN("7", 7),
    CARD_EIGHT("8", 8),
    CARD_NINE("9", 9),
    CARD_JACK("J", 10),
    CARD_QUEEN("Q", 10),
    CARD_KING("K", 10),
    CARD_ACE("A", 1);
}
