package blackjack

enum class Denomination(val score: Int) {
    ACE(1) {
        override fun toString(): String {
            return "에이스"
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
            return "잭"
        }
    },
    KING(10) {
        override fun toString(): String {
            return "킹"
        }
    },
    QUEEN(10) {
        override fun toString(): String {
            return "퀸"
        }
    };

    override fun toString(): String {
        return this.score.toString()
    }
}
