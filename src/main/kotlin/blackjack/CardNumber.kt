package blackjack

import blackjack.Card.Companion.BLACK_JACK_NUM

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
    KING(10)
}

fun CardNumber.addExecute(_score: Int): Int = when (this) {
    CardNumber.ACE -> if (_score + score > BLACK_JACK_NUM) _score + 1 else _score + score
    else -> _score + score
}
