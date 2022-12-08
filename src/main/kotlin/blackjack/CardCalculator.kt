package blackjack

import kotlin.math.abs

class CardCalculator(private val cards: List<Card>) {
    fun sum(): Int {
        val sum = cards.sumOf { it.number.value }
        return sumChangeAce(sum)
    }

    private fun sumChangeAce(sum: Int): Int {
        var changeSum = sum
        val aceCards = cards.filter { it.number is CardNumber.Ace }

        aceCards.forEach { _ ->
            val sumDiff = abs(STD_NUMBER - sum)
            val changeSumDiff = abs(STD_NUMBER - (changeSum + CardNumber.Ace.diffValue()))
            if (sumDiff > changeSumDiff)
                changeSum += CardNumber.Ace.diffValue()
        }

        return changeSum
    }

    companion object {
        const val STD_NUMBER = 21
    }
}
