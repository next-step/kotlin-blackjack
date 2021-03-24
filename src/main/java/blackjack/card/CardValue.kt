package blackjack.card

enum class CardValue(val desc: String, val value: Int, val otherValue: Int, val hasOtherValue: Boolean) {

    ONE("A", 1, 11, true),
    TWO("2", 2, 2, false),
    THREE("3", 3, 3, false),
    FOUR("4", 4, 4, false),
    FIVE("5", 5, 5, false),
    SIX("6", 6, 6, false),
    SEVEN("7", 7, 7, false),
    EIGHT("8", 8, 8, false),
    NINE("9", 9, 9, false),
    KING("K", 10, 10, false),
    QUEEN("Q", 10, 10, false),
    JACK("J", 10, 10, false);
}
