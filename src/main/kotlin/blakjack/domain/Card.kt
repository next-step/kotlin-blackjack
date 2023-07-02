package blakjack.domain

class Card(
    val suit: CardSuit,
    val rank: CardRank,
)

enum class CardSuit {
    HEART, DIAMOND, SPADE, CLOVER
}

enum class CardRank(
    val value: Int,
) {
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
    KING(10),
    ;

    fun isAce(): Boolean = (this == ACE)

    companion object {
        private const val ACE_FIRST_SCORE = 1
        private const val ACE_SECOND_SCORE = 11
        const val ACE_SCORE_DIFF = ACE_SECOND_SCORE - ACE_FIRST_SCORE
    }
}
