package blackjack.domain.deck

enum class Denomination(val exposeName: String, val score: Int, val bonus: Int = 0) {
    ACE(exposeName = "A", score = 1, bonus = 11),
    TWO(exposeName = "2", score = 2),
    THREE(exposeName = "3", score = 3),
    FOUR(exposeName = "4", score = 4),
    FIVE(exposeName = "5", score = 5),
    SIX(exposeName = "6", score = 6),
    SEVEN(exposeName = "7", score = 7),
    EIGHT(exposeName = "8", score = 8),
    NINE(exposeName = "9", score = 9),
    TEN(exposeName = "10", score = 10),
    JACK(exposeName = "J", score = 10),
    QUEEN(exposeName = "Q", score = 10),
    KING(exposeName = "K", score = 10),
}
