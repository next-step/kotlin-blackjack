package blackjack.domain.card

import blackjack.domain.participant.Hand

sealed class CardSymbol(val displayName: String, val value: Int) {
    object TWO : CardSymbol("2", 2)
    object THREE : CardSymbol("3", 3)
    object FOUR : CardSymbol("4", 4)
    object FIVE : CardSymbol("5", 5)
    object SIX : CardSymbol("6", 6)
    object SEVEN : CardSymbol("7", 7)
    object EIGHT : CardSymbol("8", 8)
    object NINE : CardSymbol("9", 9)
    object TEN : CardSymbol("10", 10)
    object JACK : CardSymbol("J", 10)
    object QUEEN : CardSymbol("Q", 10)
    object KING : CardSymbol("K", 10)
    object ACE : CardSymbol("A", ACE_MAX_NUMBER) {
        override fun count(sumOfOthers: Int): Int {
            return if (sumOfOthers + super.count(0) > Hand.BUST_THRESHOLD) {
                ACE_MIN_NUMBER
            } else {
                ACE_MAX_NUMBER
            }
        }
    }

    open fun count(sumOfOthers: Int = 0): Int {
        return this.value
    }

    companion object {
        const val ACE_MIN_NUMBER: Int = 1
        const val ACE_MAX_NUMBER: Int = 11

        fun values(): Iterable<CardSymbol> {
            return listOf(TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE)
        }
    }
}
