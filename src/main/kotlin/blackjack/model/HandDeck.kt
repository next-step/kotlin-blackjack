package blackjack.model

class HandDeck {

    private val count: Int
        get() = cards.size

    val cards: MutableCollection<TrumpCard> = mutableListOf()

    val isScoreOverThanLimitScore: Boolean
        get() = score > LIMIT_SCORE

    val gapScoreWithLimitScore: Int
        get() = LIMIT_SCORE - score

    val isLessScoreThanLimit: Boolean
        get() = score < LIMIT_SCORE

    val isSameScore: Boolean
        get() = score == LIMIT_SCORE

    private val aceCount: Int
        get() = cards.count { it.number == TrumpCardNumber.ACE }

    val score: Int
        get() {
            val score: Int = cards.sumOf { it.numberScore }
            if (score <= LIMIT_SCORE) {
                return score
            }
            return exceptedAceBonusScore(score)
        }

    private fun exceptedAceBonusScore(score: Int): Int {
        val aceCount: Int = aceCount
        var scoreWithAcePlus: Int = score
        repeat(aceCount) {
            scoreWithAcePlus -= TrumpCardNumber.ACE_PLUS_SCORE
            if (scoreWithAcePlus <= LIMIT_SCORE) {
                return scoreWithAcePlus
            }
        }
        return scoreWithAcePlus
    }

    fun equalsCountOf(count: Int): Boolean {
        return this.count == count
    }

    fun isLessThanOrEqualTo(score: Int): Boolean {
        return this.score <= score
    }

    fun add(card: TrumpCard) {
        cards.add(card)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HandDeck

        return cards == other.cards
    }

    override fun hashCode(): Int {
        return cards.hashCode()
    }

    override fun toString(): String {
        return "HandDeck(cards=$cards)"
    }

    companion object {
        const val LIMIT_SCORE = 21
    }
}
