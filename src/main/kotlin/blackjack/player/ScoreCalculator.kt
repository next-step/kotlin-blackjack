package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber

object ScoreCalculator {

    fun calculateScore(hand: Hand): Int {
        var totalValue = 0
        var numberOfAces = 0

        hand.forEachCard { card ->
            val (value, isAce) = calculateCardValue(card)
            totalValue += value
            numberOfAces += if (isAce) 1 else 0
        }

        return adjustForAces(totalValue, numberOfAces)
    }

    private fun calculateCardValue(card: Card): Pair<Int, Boolean> {
        return if (card.number == CardNumber.ACE) {
            Pair(11, true)
        } else {
            Pair(card.number.maxValue, false)
        }
    }

    private fun adjustForAces(totalValue: Int, numberOfAces: Int): Int {
        var adjustedValue = totalValue
        var remainingAces = numberOfAces

        while (adjustedValue > 21 && remainingAces > 0) {
            adjustedValue -= 10
            remainingAces--
        }

        return adjustedValue
    }
}
