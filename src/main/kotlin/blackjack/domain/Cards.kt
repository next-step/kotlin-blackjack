package blackjack.domain

class Cards(vararg cards: Card) {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    init {
        _cards.addAll(cards)
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    fun canDrawMoreCard(): Boolean {
        return calculateScore() < BLACKJACK
    }

    fun isBlackjack(): Boolean {
        return calculateScore() == BLACKJACK
    }

    fun isBust(): Boolean {
        return calculateScore() > BLACKJACK
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
