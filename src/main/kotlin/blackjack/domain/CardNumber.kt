package blackjack.domain

enum class CardNumber(val display: String, val value: Int) {
    ACE("A", 11),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10),
    NINE("9", 9),
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2),
    ;

    companion object {
        const val ACE_SUB_VALUE: Int = 1
    }
}
