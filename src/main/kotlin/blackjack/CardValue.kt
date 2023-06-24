package blackjack

enum class CardValue {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE;

    companion object {
        fun CardValue.toPoint(sum: Int): Int {
            return when (this) {
                TWO -> 2
                THREE -> 3
                FOUR -> 4
                FIVE -> 5
                SIX -> 6
                SEVEN -> 7
                EIGHT -> 8
                NINE -> 9
                ACE -> if (sum + 11 > Round.BLACK_JACK) 1 else 11
                else -> 10
            }
        }

        fun CardValue.toText(): String {
            return when (this) {
                TWO -> "2"
                THREE -> "3"
                FOUR -> "4"
                FIVE -> "5"
                SIX -> "6"
                SEVEN -> "7"
                EIGHT -> "8"
                NINE -> "9"
                TEN -> "10"
                JACK -> "J"
                QUEEN -> "Q"
                KING -> "K"
                ACE -> "A"
            }
        }
    }
}
