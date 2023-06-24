package blakjack.domain

object CardsScoreCalculator {
    fun sum(cards: Cards): Int {
        var sum = sumByScore(cards)
        repeat(getAceCount(cards)) {
            if (isBust(sum + Card.ACE_SCORE_DIFF)) return@repeat
            sum += Card.ACE_SCORE_DIFF
        }

        return sum
    }

    fun sumWithAceAsOne(cards: Cards): Int {
        return sumByScore(cards)
    }

    private fun isBust(score: Int): Boolean {
        return score > BLACKJACK_SCORE
    }

    private fun getAceCount(cards: Cards): Int {
        return cards.values.count(Card::isAce)
    }

    private fun sumByScore(cards: Cards): Int {
        return cards.values.sumOf(Card::score)
    }
}
