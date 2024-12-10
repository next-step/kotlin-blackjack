package study.blackjack.model

/**
 * @author 이상준
 */
enum class Suit {
    SPADE,
    DIAMOND,
    HEART,
    CLUB,
    ;

    companion object {
        fun findSuit(number: Int): Suit? {
            return entries.find { it.ordinal == number }
        }
    }
}
