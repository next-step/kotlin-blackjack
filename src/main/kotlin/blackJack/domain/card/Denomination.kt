package blackJack.domain.card

enum class Denomination(val value: String, val orderBy: Int) : CardScore {
    ACE("A", 1) {
        override fun score(sum: Int): Int =
            if (sum > 21) {
                1
            } else {
                11
            }
    },
    TWO("2", 2) {
        override fun score(sum: Int): Int = 2
    },
    THREE("3", 3) {
        override fun score(sum: Int): Int = 3
    },
    FOUR("4", 4) {
        override fun score(sum: Int): Int = 4
    },
    FIVE("5", 5) {
        override fun score(sum: Int): Int = 5
    },
    SIX("6", 6) {
        override fun score(sum: Int): Int = 6
    },
    SEVEN("7", 7) {
        override fun score(sum: Int): Int = 7
    },
    EIGHT("8", 8) {
        override fun score(sum: Int): Int = 8
    },
    NINE("9", 9) {
        override fun score(sum: Int): Int = 9
    },
    TEN("10", 10) {
        override fun score(sum: Int): Int = 10
    },
    JACK("J", 11) {
        override fun score(sum: Int): Int = 10
    },
    QUEEN("Q", 12) {
        override fun score(sum: Int): Int = 10
    },
    KING("K", 13) {
        override fun score(sum: Int): Int = 10
    };
}
