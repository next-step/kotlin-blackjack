package blackjack.domain

import kotlin.math.abs

data class Card(val value: Pair<Number, Pattern>) {
    val displayName: String
        get() = value.first.displayName + value.second.pattern

    fun getCardNumber(sum: Int): Int {
        if (checkCardIsAce()) {
            return selectAceIsBetterNumber(sum)
        }
        return value.first.score
    }

    fun checkCardIsAce(): Boolean {
        return value.first == Number.ACE
    }

    private fun selectAceIsBetterNumber(sum: Int): Int {
        if (sum + 11 <= Cards.WINNING_NUMBER) {
            return 11
        }
        return 1
    }
}
