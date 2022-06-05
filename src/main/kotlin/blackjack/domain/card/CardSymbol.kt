package blackjack.domain.card

import blackjack.domain.player.Hand
import blackjack.util.ACE_MAX_NUMBER
import blackjack.util.ACE_MIN_NUMBER

enum class CardSymbol(val displayName: String, private val value: Int) {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", ACE_MAX_NUMBER) {
        override fun count(sumOfOthers: Int): Int {
            return if (sumOfOthers + super.count(0) > Hand.BUST_THRESHOLD) {
                ACE_MIN_NUMBER
            } else {
                ACE_MAX_NUMBER
            }
        }
    };

    open fun count(sumOfOthers: Int = 0): Int {
        return this.value
    }
}
