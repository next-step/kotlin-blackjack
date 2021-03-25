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

    private fun checkCardIsAce(): Boolean {
        return value.first == Number.ACE
    }

    private fun selectAceIsBetterNumber(sum: Int): Int {
        val whenAceIs11 = abs(Cards.WINNING_NUMBER - (sum + 11))
        val whenAceIs1 = abs(Cards.WINNING_NUMBER - (sum + 1))

        if (whenAceIs11 < whenAceIs1) {
            return 11
        }
        return 1
    }
}
