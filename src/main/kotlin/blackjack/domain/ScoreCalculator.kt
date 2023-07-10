package blackjack.domain

import blackjack.domain.ScoreValidator.isValidBlackjackScoreWithAceAsEleven

object ScoreCalculator {
    fun calculateScore(cards: Cards): Int {
        var score = cards.sumOfScoreWithAceAsOne

        repeat(cards.numberOfAce) {
            if (isValidBlackjackScoreWithAceAsEleven(score)) {
                score += Rank.ACE_MAX - Rank.ACE_MIN
            }
        }
        return score
    }
}
