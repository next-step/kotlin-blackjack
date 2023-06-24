package blakjack.domain

enum class Card(
    val score: Int,
    val korean: String,
) {
    HEART_ACE(1, "A하트"),
    HEART_2(2, "2하트"),
    HEART_3(3, "3하트"),
    HEART_4(4, "4하트"),
    HEART_5(5, "5하트"),
    HEART_6(6, "6하트"),
    HEART_7(7, "7하트"),
    HEART_8(8, "8하트"),
    HEART_9(9, "9하트"),
    HEART_10(10, "10하트"),
    HEART_JACK(10, "J하트"),
    HEART_QUEEN(10, "Q하트"),
    HEART_KING(10, "K하트"),
    DIAMOND_ACE(1, "A다이아몬드"),
    DIAMOND_2(2, "2다이아몬드"),
    DIAMOND_3(3, "3다이아몬드"),
    DIAMOND_4(4, "4다이아몬드"),
    DIAMOND_5(5, "5다이아몬드"),
    DIAMOND_6(6, "6다이아몬드"),
    DIAMOND_7(7, "7다이아몬드"),
    DIAMOND_8(8, "8다이아몬드"),
    DIAMOND_9(9, "9다이아몬드"),
    DIAMOND_10(10, "10다이아몬드"),
    DIAMOND_JACK(10, "J다이아몬드"),
    DIAMOND_QUEEN(10, "Q다이아몬드"),
    DIAMOND_KING(10, "K다이아몬드"),
    SPADE_ACE(1, "A스페이드"),
    SPADE_2(2, "2스페이드"),
    SPADE_3(3, "3스페이드"),
    SPADE_4(4, "4스페이드"),
    SPADE_5(5, "5스페이드"),
    SPADE_6(6, "6스페이드"),
    SPADE_7(7, "7스페이드"),
    SPADE_8(8, "8스페이드"),
    SPADE_9(9, "9스페이드"),
    SPADE_10(10, "10스페이드"),
    SPADE_JACK(10, "J스페이드"),
    SPADE_QUEEN(10, "Q스페이드"),
    SPADE_KING(10, "K스페이드"),
    CLOVER_ACE(1, "A클로버"),
    CLOVER_2(2, "2클로버"),
    CLOVER_3(3, "3클로버"),
    CLOVER_4(4, "4클로버"),
    CLOVER_5(5, "5클로버"),
    CLOVER_6(6, "6클로버"),
    CLOVER_7(7, "7클로버"),
    CLOVER_8(8, "8클로버"),
    CLOVER_9(9, "9클로버"),
    CLOVER_10(10, "10클로버"),
    CLOVER_JACK(10, "J클로버"),
    CLOVER_QUEEN(10, "Q클로버"),
    CLOVER_KING(10, "K클로버"),
    ;

    fun isAce(): Boolean {
        return this == HEART_ACE || this == DIAMOND_ACE || this == SPADE_ACE || this == CLOVER_ACE
    }

    companion object {
        private const val ACE_FIRST_SCORE = 1
        private const val ACE_SECOND_SCORE = 11
        const val ACE_SCORE_DIFF = ACE_SECOND_SCORE - ACE_FIRST_SCORE
    }
}
