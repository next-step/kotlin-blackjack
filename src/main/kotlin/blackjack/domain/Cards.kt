package blackjack.domain

class Cards {
    val cards: MutableList<Card> = mutableListOf()

    fun add(card: Card) {
        cards.add(card)
    }

    fun canDrawMoreCard(): Boolean {
        return calculateScore() < BLACKJACK
    }

    fun calculateScore(): Int {
        val hasAce = cards.any { it.denomination == Denomination.ACE }

        val totalScore = cards.sumOf { it.denomination.score }
        if (hasAce && totalScore <= BLACKJACK - ACE_SCORE_DIFF) {
            return totalScore + ACE_SCORE_DIFF
        }
        return totalScore
    }

    companion object {
        const val BLACKJACK = 21
        private const val ACE_SCORE_ONE = 1
        private const val ACE_SCORE_ELEVEN = 11
        const val ACE_SCORE_DIFF = ACE_SCORE_ELEVEN - ACE_SCORE_ONE
    }
}
