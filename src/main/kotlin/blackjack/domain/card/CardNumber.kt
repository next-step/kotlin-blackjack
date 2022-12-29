package blackjack.domain.card

enum class CardNumber(val mainScore: Int, val secondaryScore: Int) {
    ACE(11, 1) { override fun toString() = "A" },
    TWO(2, 2) { override fun toString() = "2" },
    THREE(3, 3) { override fun toString() = "3" },
    FOUR(4, 4) { override fun toString() = "4" },
    FIVE(5, 5) { override fun toString() = "5" },
    SIX(6, 6) { override fun toString() = "6" },
    SEVEN(7, 7) { override fun toString() = "7" },
    EIGHT(8, 8) { override fun toString() = "8" },
    NINE(9, 9) { override fun toString() = "9" },
    TEN(10, 10) { override fun toString() = "10" },
    JACK(10, 10) { override fun toString() = "J" },
    QUEEN(10, 10) { override fun toString() = "Q" },
    KING(10, 10) { override fun toString() = "K" }
    ;
}
