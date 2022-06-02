package blackjack

enum class CardNumber(val cardName: String, val number: Int, val isMultiple: Boolean, val multiNumbers: List<Int>?) {
    ACE("Ace", 1, true, listOf(1, 10)),
    TWO("2", 2, false, null),
    THREE("3", 3, false, null),
    FOUR("4", 4, false, null),
    FIVE("5", 5, false, null),
    SIX("6", 6, false, null),
    SEVEN("7", 7, false, null),
    EIGHT("8", 8, false, null),
    NINE("9", 9, false, null),
    JACK("Jack", 10, false, null),
    QUEEN("Queen", 10, false, null),
    KING("King", 10, false, null),
}
