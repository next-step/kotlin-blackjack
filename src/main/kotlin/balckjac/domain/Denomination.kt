package balckjac.domain

enum class Denomination(
    val cardName: String,
    val score: Int,
    val multipleScore: List<Int>
) {
    ACE("Ace", 1, listOf(1, 11)),
    TWO("2", 2, emptyList()),
    THREE("3", 3, emptyList()),
    FOUR("4", 4, emptyList()),
    FIVE("5", 5, emptyList()),
    SIX("6", 6, emptyList()),
    SEVEN("7", 7, emptyList()),
    EIGHT("8", 8, emptyList()),
    NINE("9", 9, emptyList()),
    JACK("Jack", 10, emptyList()),
    QUEEN("Queen", 10, emptyList()),
    KING("King", 10, emptyList())
    ;

    val isMultipleCard = multipleScore.isNotEmpty()
}
