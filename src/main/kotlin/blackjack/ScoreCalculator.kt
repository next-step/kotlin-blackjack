package blackjack

import blackjack.domains.deck.Cards

class ScoreCalculator(private val cards: Cards) {

    fun sumOfNumbers(defeatedMaxNumber: Int): Int {
        if (cards.hasAce()) {
            return sumWithAce(defeatedMaxNumber)
        }
        return cards.values.sumOf { it.pokerNumber.number }
    }

    private fun sumWithAce(defeatedMaxNumber: Int): Int {
        val summedNumber = cards.values.sumOf { it.pokerNumber.number }
        if (summedNumber + 10 <= defeatedMaxNumber) {
            return summedNumber + 10
        }
        return summedNumber
    }
}
