package blackjack.domain

enum class Rank(val value: String, val score: Int) {
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
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ;

    companion object {
        fun from(value: String): Rank {
            return entries.firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("유효하지 않은 랭크 값입니다: $value")
        }
    }
}
