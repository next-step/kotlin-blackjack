package nextstep.blackjack

enum class Card(private val point: Int) {
    HEART_ACE(11),
    HEART_ONE(1),
    HEART_TWO(2),
    HEART_THREE(3),
    HEART_FOUR(4),
    HEART_FIVE(5),
    HEART_SIX(6),
    HEART_SEVEN(7),
    HEART_EIGHT(8),
    HEART_NINE(9),
    HEART_TEN(10),
    HEART_JACK(10),
    HEART_QUEEN(10),
    HEART_KING(10),

    DIAMOND_ACE(11),
    DIAMOND_ONE(1),
    DIAMOND_TWO(2),
    DIAMOND_THREE(3),
    DIAMOND_FOUR(4),
    DIAMOND_FIVE(5),
    DIAMOND_SIX(6),
    DIAMOND_SEVEN(7),
    DIAMOND_EIGHT(8),
    DIAMOND_NINE(9),
    DIAMOND_TEN(10),
    DIAMOND_JACK(10),
    DIAMOND_QUEEN(10),
    DIAMOND_KING(10),

    SPADE_ACE(11),
    SPADE_ONE(1),
    SPADE_TWO(2),
    SPADE_THREE(3),
    SPADE_FOUR(4),
    SPADE_FIVE(5),
    SPADE_SIX(6),
    SPADE_SEVEN(7),
    SPADE_EIGHT(8),
    SPADE_NINE(9),
    SPADE_TEN(10),
    SPADE_JACK(10),
    SPADE_QUEEN(10),
    SPADE_KING(10),

    CLOVER_ACE(11),
    CLOVER_ONE(1),
    CLOVER_TWO(2),
    CLOVER_THREE(3),
    CLOVER_FOUR(4),
    CLOVER_FIVE(5),
    CLOVER_SIX(6),
    CLOVER_SEVEN(7),
    CLOVER_EIGHT(8),
    CLOVER_NINE(9),
    CLOVER_TEN(10),
    CLOVER_JACK(10),
    CLOVER_QUEEN(10),
    CLOVER_KING(10),
    ;

    fun getPoint(currentPoint: Int): Int = when {
        ACE_CARDS.contains(this).not() -> this.point
        this.point + currentPoint > 21 -> 1
        else -> this.point
    }

    fun isAce(): Boolean = ACE_CARDS.contains(this)

    companion object {
        private val ACE_CARDS: Set<Card> = setOf(DIAMOND_ACE, CLOVER_ACE, HEART_ACE, SPADE_ACE)
    }
}
