package nextstep.blackjack

enum class Card(
    private val point: Int,
    val description: String
) {
    HEART_ACE(11, "A하트"),
    HEART_ONE(1, "1하트"),
    HEART_TWO(2, "2하트"),
    HEART_THREE(3, "3하트"),
    HEART_FOUR(4, "4하트"),
    HEART_FIVE(5, "5하트"),
    HEART_SIX(6, "6하트"),
    HEART_SEVEN(7, "7하트"),
    HEART_EIGHT(8, "8하트"),
    HEART_NINE(9, "9하트"),
    HEART_TEN(10, "10하트"),
    HEART_JACK(10, "J하트"),
    HEART_QUEEN(10, "Q하트"),
    HEART_KING(10, "K하트"),

    DIAMOND_ACE(11, "A다이이아몬드"),
    DIAMOND_ONE(1, "1다이이아몬드"),
    DIAMOND_TWO(2, "2다이이아몬드"),
    DIAMOND_THREE(3, "3다이이아몬드"),
    DIAMOND_FOUR(4, "4다이이아몬드"),
    DIAMOND_FIVE(5, "5다이이아몬드"),
    DIAMOND_SIX(6, "6다이이아몬드"),
    DIAMOND_SEVEN(7, "7다이이아몬드"),
    DIAMOND_EIGHT(8, "8다이이아몬드"),
    DIAMOND_NINE(9, "9다이이아몬드"),
    DIAMOND_TEN(10, "10다이이아몬드"),
    DIAMOND_JACK(10, "J다이이아몬드"),
    DIAMOND_QUEEN(10, "Q다이이아몬드"),
    DIAMOND_KING(10, "K다이이아몬드"),

    SPADE_ACE(11, "A스페이드"),
    SPADE_ONE(1, "1스페이드"),
    SPADE_TWO(2, "2스페이드"),
    SPADE_THREE(3, "3스페이드"),
    SPADE_FOUR(4, "4스페이드"),
    SPADE_FIVE(5, "5스페이드"),
    SPADE_SIX(6, "6스페이드"),
    SPADE_SEVEN(7, "7스페이드"),
    SPADE_EIGHT(8, "8스페이드"),
    SPADE_NINE(9, "9스페이드"),
    SPADE_TEN(10, "10스페이드"),
    SPADE_JACK(10, "J스페이드"),
    SPADE_QUEEN(10, "Q스페이드"),
    SPADE_KING(10, "K스페이드"),

    CLOVER_ACE(11, "A클로버"),
    CLOVER_ONE(1, "1클로버"),
    CLOVER_TWO(2, "2클로버"),
    CLOVER_THREE(3, "3클로버"),
    CLOVER_FOUR(4, "4클로버"),
    CLOVER_FIVE(5, "5클로버"),
    CLOVER_SIX(6, "6클로버"),
    CLOVER_SEVEN(7, "7클로버"),
    CLOVER_EIGHT(8, "8클로버"),
    CLOVER_NINE(9, "9클로버"),
    CLOVER_TEN(10, "1클로버드"),
    CLOVER_JACK(10, "J클로버"),
    CLOVER_QUEEN(10, "Q클로버"),
    CLOVER_KING(10, "K클로버"),
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
