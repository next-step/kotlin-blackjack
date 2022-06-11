package blackjack.card

enum class CardNumber(val noName: String, val primaryPoint: Int, val secondaryPoint: Int) {
    ACE("Ace", 10, 1),
    TWO("2", 2, 0),
    THREE("3", 3, 0),
    FOUR("4", 4, 0),
    FIVE("5", 5, 0),
    SIX("6", 6, 0),
    SEVEN("7", 7, 0),
    EIGHT("8", 8, 0),
    NINE("9", 9, 0),
    JACK("Jack", 10, 0),
    QUEEN("Queen", 10, 0),
    KING("King", 10, 0);
}
