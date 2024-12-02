package study.blackjack.model

/**
 * @author 이상준
 */
enum class Suit(
    val code: Int,
    val suitName: String,
) {
    SPADE(0, "스페이드"),
    DIAMOND(1, "다이아"),
    HEART(2, "하트"),
    CLUB(3, "클로버"),
    ;

    companion object {
        fun findSuit(
            code: Int,
        ): Suit? {
            return entries.find { it.code == code }
        }
    }
}
