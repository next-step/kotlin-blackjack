package domain.card

enum class Denomination(val numbers: Set<Int>) {
    ACE(setOf(1, 11)) {
        override fun toString() = "A"
    },
    TWO(setOf(2)) {
        override fun toString() = "2"
    },
    THREE(setOf(3)) {
        override fun toString() = "3"
    },
    FOUR(setOf(4)) {
        override fun toString() = "4"
    },
    FIVE(setOf(5)) {
        override fun toString() = "5"
    },
    SIX(setOf(6)) {
        override fun toString() = "6"
    },
    SEVEN(setOf(7)) {
        override fun toString() = "7"
    },
    EIGHT(setOf(8)) {
        override fun toString() = "8"
    },
    NINE(setOf(9)) {
        override fun toString() = "9"
    },
    TEN(setOf(10)) {
        override fun toString() = "10"
    },
    KING(setOf(10)) {
        override fun toString() = "K"
    },
    QUEEN(setOf(10)) {
        override fun toString() = "Q"
    },
    JACK(setOf(10)) {
        override fun toString() = "J"
    };
}
