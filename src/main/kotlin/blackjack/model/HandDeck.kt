package blackjack.model

class HandDeck {

    val cards: MutableCollection<TrumpCard> = mutableListOf()

    val isScoreOverThanLimitScore: Boolean
        get() = score > LIMIT_SCORE

    private val aceCount: Int
        get() = cards.count { it.number == TrumpCardNumber.ACE }

    val score: Int
        get() {
            val score: Int = cards.sumOf { it.number.score }
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

    operator fun plus(card: TrumpCard) {
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
