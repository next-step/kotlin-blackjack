package model

enum class PokerNumber(val desc: String, val score: Int) {
    ACE("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("Jack", 10),
    QUEEN("Queen", 10),
    KING("King", 10);

    companion object {
        fun find(desc: String): PokerNumber =
            PokerNumber.values().find { it.desc == desc }
                ?: throw IllegalArgumentException("해당 원소를 찾을 수 없습니다")
    }
}
