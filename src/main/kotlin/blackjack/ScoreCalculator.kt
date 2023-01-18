package blackjack

import blackjack.domains.deck.Cards

object ScoreCalculator {

    fun sumOfNumbers(cards: Cards, defeatedMaxNumber: Int): Int {
        if (cards.hasAce()) {
            return sumWithAce(cards, defeatedMaxNumber)
        }
        return cards.values.sumOf { it.pokerNumber.number }
    }

    private fun sumWithAce(cards: Cards, defeatedMaxNumber: Int): Int {
        val summedNumber = cards.values.sumOf { it.pokerNumber.number }
        if (summedNumber + 10 <= defeatedMaxNumber) {
            return summedNumber + 10
        }
        return summedNumber
    }
}
