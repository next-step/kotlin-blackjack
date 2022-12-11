package blackjack.domain

enum class Denomination(val score: Int) {
    ACE(1) {
        override fun toString(): String {
            return "A"
        }
    },
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10) {
        override fun toString(): String {
            return "J"
        }
    },
    KING(10) {
        override fun toString(): String {
            return "K"
        }
    },
    QUEEN(10) {
        override fun toString(): String {
            return "Q"
        }
    };

    override fun toString(): String {
        return this.score.toString()
    }
}
