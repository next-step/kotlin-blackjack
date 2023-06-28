package blackjack.player

import blackjack.card.CardNumber

object ScoreCalculator {

    fun calculateScore(hand: Hand): Int {
        var totalValue = 0
        var numberOfAces = 0

        hand.forEachCard { card ->
            totalValue += card.number.maxValue
            numberOfAces += if (card.number == CardNumber.ACE) 1 else 0
        }

        return adjustForAces(totalValue, numberOfAces)
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
