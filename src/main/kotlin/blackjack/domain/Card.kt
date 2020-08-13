package blackjack.domain

const val BLACK_JACK = 21

enum class Card(val value: Int, val cardName: String) {
    CLUB_ACE(11, "A클럽"),
    CLUB_2(2, "2클럽"),
    CLUB_3(3, "3클럽"),
    CLUB_4(4, "4클럽"),
    CLUB_5(5, "5클럽"),
    CLUB_6(6, "6클럽"),
    CLUB_7(7, "7클럽"),
    CLUB_8(8, "8클럽"),
    CLUB_9(9, "9클럽"),
    CLUB_10(10, "10클럽"),
    CLUB_J(10, "J클럽"),
    CLUB_Q(10, "Q클럽"),
    CLUB_K(10, "K클럽"),

    SPADE_ACE(11, "A스페이드"),
    SPADE_2(2, "2스페이드"),
    SPADE_3(3, "3스페이드"),
    SPADE_4(4, "4스페이드"),
    SPADE_5(5, "5스페이드"),
    SPADE_6(6, "6스페이드"),
    SPADE_7(7, "7스페이드"),
    SPADE_8(8, "8스페이드"),
    SPADE_9(9, "9스페이드"),
    SPADE_10(10, "10스페이드"),
    SPADE_J(10, "J스페이드"),
    SPADE_Q(10, "Q스페이드"),
    SPADE_K(10, "K스페이드"),

    HEART_ACE(11, "A하트"),
    HEART_2(2, "2하트"),
    HEART_3(3, "3하트"),
    HEART_4(4, "4하트"),
    HEART_5(5, "5하트"),
    HEART_6(6, "6하트"),
    HEART_7(7, "7하트"),
    HEART_8(8, "8하트"),
    HEART_9(9, "9하트"),
    HEART_10(10, "10하트"),
    HEART_J(10, "J하트"),
    HEART_Q(10, "Q하트"),
    HEART_K(10, "K하트"),

    DIAMOND_ACE(11, "A다이아몬드"),
    DIAMOND_2(2, "2다이아몬드"),
    DIAMOND_3(3, "3다이아몬드"),
    DIAMOND_4(4, "4다이아몬드"),
    DIAMOND_5(5, "5다이아몬드"),
    DIAMOND_6(6, "6다이아몬드"),
    DIAMOND_7(7, "7다이아몬드"),
    DIAMOND_8(8, "8다이아몬드"),
    DIAMOND_9(9, "9다이아몬드"),
    DIAMOND_10(10, "10다이아몬드"),
    DIAMOND_J(10, "J다이아몬드"),
    DIAMOND_Q(10, "Q다이아몬드"),
    DIAMOND_K(10, "K다이아몬드");

    fun isAce(): Boolean {
        return this.value == 11
    }

    companion object {
        val PACK = values().toList()
    }
}
