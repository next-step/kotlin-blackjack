package study.blackjack.model

/**
 * @author 이상준
 */
enum class PlayingCards(val number: Int, val cardName: String) {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ;

    companion object {
        fun findPlayingCard(number: Int): PlayingCards {
            return entries.find { it.number == number } ?: throw IllegalArgumentException("잘못된 카드 숫자입니다.")
        }
    }

    fun score(isAce: Boolean = true): Int {
        if (this == ACE && isAce) {
            return 11
        } else if (this in (JACK..KING)) {
            return 10
        }

        return this.number
    }
}
