package blackjack.domain

import blackjack.domain.card.Cards

object ScoreCalculator {
    const val BLACKJACK_LIMIT = 21
    private const val ACE_BONUS_SCORE = 10

    fun calculateScore(cards: Cards): Int {
        var sum = cards.sumOf { it.cardNumber.score }
        if (cards.hasAce() && sum + ACE_BONUS_SCORE <= BLACKJACK_LIMIT) {
            sum += ACE_BONUS_SCORE
        }
        return sum
    }
}
