package blackjack.domain

object CardScoreCalculator {
    private const val DEFAULT_TOTAL_SCORE_VALUE = 0

    fun calculate(cards: List<Card>): Score {
        val candidates = mutableSetOf<Int>()
        calculate(cards, cards.size - 1, DEFAULT_TOTAL_SCORE_VALUE, candidates)

        return pickOptimizedScore(candidates)
    }

    private fun calculate(cards: List<Card>, index: Int, total: Int, candidates: MutableSet<Int>) {
        if (index < 0) {
            candidates.add(total)
            return
        }
        if (cards[index].spell.isAce()) {
            calculate(cards, index - 1, total + CardSpell.ACE_MINOR_DIGIT, candidates)
        }
        calculate(cards, index - 1, total + cards[index].getDigit(), candidates)
    }

    private fun pickOptimizedScore(candidates: MutableSet<Int>): Score {
        if (candidates.all { it > GameConfig.BUST_CONDITION }) return Score(
            candidates.min() ?: DEFAULT_TOTAL_SCORE_VALUE, true
        )
        return Score(candidates.filter { it <= GameConfig.BUST_CONDITION }.max() ?: DEFAULT_TOTAL_SCORE_VALUE)
    }
}
