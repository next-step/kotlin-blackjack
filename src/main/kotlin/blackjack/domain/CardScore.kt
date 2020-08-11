package blackjack.domain

enum class CardScore(val score: Int) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    companion object {

        fun initialOfCard(cardScore: CardScore): String {
            return when (cardScore) {
                ACE -> "A"
                JACK -> "J"
                QUEEN -> "Q"
                KING -> "K"
                else -> cardScore.score.toString()
            }
        }
    }
}
