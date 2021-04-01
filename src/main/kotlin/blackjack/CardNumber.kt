package blackjack

import blackjack.User.Companion.BLACK_JACK_NUM

enum class CardNumber(val score: Int) {
    ACE(11),
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
    KING(10);

    fun addExecute(_score: Int): Int = when (this) {
        ACE -> if (_score + score > BLACK_JACK_NUM) _score + ACE_LOW_SCORE else _score + score
        else -> _score + score
    }

    companion object {
        const val ACE_LOW_SCORE = 1
    }
}
