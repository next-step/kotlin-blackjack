package study.blackjack.model

/**
 * @author 이상준
 */
enum class CardRank(val score: Int) {
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

    fun score(): Int = this.score

    companion object {
        fun findCardRank(number: Int): CardRank {
            return entries.find { it.ordinal == number } ?: throw IllegalArgumentException("잘못된 카드 숫자입니다.")
        }
    }
}
