package blackjack.domain

import kotlin.math.min

class GameUser(val name: String) {
    val cards = mutableListOf<BlackJackCard>()
    val points: Int
        get() {
            return calculatePoints()
        }

    fun addCard(card: BlackJackCard) {
        cards.add(card)
    }

    private fun calculatePoints(): Int {
        var sumPoint = 0
        var aceCount = 0
        var shouldPoint = 0
        cards.forEach {
            val point = it.getPoint()
            sumPoint += point
            aceCount += if (point == 1) 1 else 0
            shouldPoint = checkAce(sumPoint, aceCount)
        }
        return shouldPoint
    }

    private fun checkAce(
        sumPoint: Int,
        aceCount: Int,
    ): Int {
        if (sumPoint >= 21) return sumPoint
        val shouldConsumeCount = (21 - sumPoint) / 10
        return sumPoint + (min(shouldConsumeCount, aceCount) * 10)
    }
}
