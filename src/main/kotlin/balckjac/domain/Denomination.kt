package balckjac.domain

enum class Denomination(
    val cardName: String,
    val score: Int,
    val anotherScore: Int
) {
    ACE("Ace", 1, 11),
    TWO("2", 2, 0),
    THREE("3", 3, 0),
    FOUR("4", 4, 0),
    FIVE("5", 5, 0),
    SIX("6", 6, 0),
    SEVEN("7", 7, 0),
    EIGHT("8", 8, 0),
    NINE("9", 9, 0),
    TEN("10", 10, 0),
    JACK("Jack", 10, 0),
    QUEEN("Queen", 10, 0),
    KING("King", 10, 0)
    ;

    val isSingleScore = anotherScore == 0
}
