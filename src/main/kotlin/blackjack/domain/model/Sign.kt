package blackjack.domain.model

enum class Sign(val sign: String, val number: Int, val secretNumber: Int) {
    ACE("A", 1, 11),
    TWO("2", 2, 0),
    THREE("3", 3, 0),
    FOUR("4", 4, 0),
    FIVE("5", 5, 0),
    SIX("6", 6, 0),
    SEVEN("7", 7, 0),
    EIGHT("8", 8, 0),
    NINE("9", 9, 0),
    TEN("10", 10, 0),
    JACK("J", 10, 0),
    QUEEN("Q", 10, 0),
    KING("K", 10, 0);
}
