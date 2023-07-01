package blackjack.domain

enum class Rank(val symbol: String, val score: Int) {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SECEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10);

    companion object {
        const val ACE_MIN = 1
        const val ACE_MAX = 11

        fun random(): Rank {
            return values().random()
        }
    }
}