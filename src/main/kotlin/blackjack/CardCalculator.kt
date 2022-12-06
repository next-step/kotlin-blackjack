package blackjack

import kotlin.math.abs

class CardCalculator(private val cards: List<Card>) {
    fun sum(): Int {
        val sum = cards.sumOf { if (it.number.value > 10) 10 else it.number.value }
        return sumChangeAce(sum)
    }

    private fun sumChangeAce(sum: Int): Int {
        var changeSum = sum
        cards.filter { it.number.value == 1 }.forEach { _ ->
            val sumDiff = abs(STD_NUMBER - sum)
            val changeSumDiff = abs(STD_NUMBER - (changeSum + 10))
            if (sumDiff > changeSumDiff)
                changeSum += 10
        }
        return changeSum
    }

    companion object {
        const val STD_NUMBER = 21
    }
}
