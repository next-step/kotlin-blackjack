package blackJack.domain.card

import blackJack.domain.card.Signal.Companion.MAX_NUMBER

enum class Denomination(val value: String, val orderBy: Int, val score: (sum: Int) -> Int) {
    ACE("A", 1, {
        if (it + DOWN_SCORE_ACE > MAX_NUMBER) {
            TOP_SCORE_ACE
        } else {
            DOWN_SCORE_ACE
        }
    }),
    TWO("2", 2, { 2 }),
    THREE("3", 3, { 3 }),
    FOUR("4", 4, { 4 }),
    FIVE("5", 5, { 5 }),
    SIX("6", 6, { 6 }),
    SEVEN("7", 7, { 7 }),
    EIGHT("8", 8, { 8 }),
    NINE("9", 9, { 9 }),
    TEN("10", 10, { 10 }),
    JACK("J", 11, { 10 }),
    QUEEN("Q", 12, { 10 }),
    KING("K", 13, { 10 });

    companion object {
        private const val DOWN_SCORE_ACE = 11
        private const val TOP_SCORE_ACE = 1
    }
}
