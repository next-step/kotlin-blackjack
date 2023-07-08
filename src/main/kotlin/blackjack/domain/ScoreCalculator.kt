package blackjack.domain

object ScoreCalculator {
    fun calculateScore(cards: Cards): Int {
        var score = cards.sumOfScoreWithAceAsOne

        repeat(cards.numberOfAce) {
            if (ScoreValidator.isValidBlackjackScoreWithAceAsEleven(score)) {
                score += Rank.ACE_MAX - Rank.ACE_MIN
            }
        }
        return score
    }
}
