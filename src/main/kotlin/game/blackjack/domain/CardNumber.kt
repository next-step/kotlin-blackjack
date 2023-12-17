package game.blackjack.domain

enum class CardNumber(private val score: Int, val value: String) {
    ACE(1, "A") {
        override fun getScore(currentScore: Int) = if (currentScore + 11 <= 21) 11 else 1
    },
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K")
    ;

    open fun getScore(currentScore: Int = 0) = this.score
}
