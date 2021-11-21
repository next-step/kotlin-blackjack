package blackJack.domain.card

enum class Denomination : CardScore {
    ACE {
        override fun score(sum: Int): Int =
            if (sum > 21) {
                1
            } else {
                11
            }
    },
    TWO {
        override fun score(sum: Int): Int = 2
    },
    THREE {
        override fun score(sum: Int): Int = 3
    },
    FOUR {
        override fun score(sum: Int): Int = 4
    },
    FIVE {
        override fun score(sum: Int): Int = 5
    },
    SIX {
        override fun score(sum: Int): Int = 6
    },
    SEVEN {
        override fun score(sum: Int): Int = 7
    },
    EIGHT {
        override fun score(sum: Int): Int = 8
    },
    NINE {
        override fun score(sum: Int): Int = 9
    },
    TEN {
        override fun score(sum: Int): Int = 10
    },
    JACK {
        override fun score(sum: Int): Int = 10
    },
    QUEEN {
        override fun score(sum: Int): Int = 10
    },
    KING {
        override fun score(sum: Int): Int = 10
    };
}
