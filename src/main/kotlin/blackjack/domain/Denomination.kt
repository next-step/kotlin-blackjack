package blackjack.domain

enum class Denomination(private val primaryScore: Score, private val secondaryScore: Score) {

    ACE(primaryScore = Score(11), secondaryScore = Score(1)),
    TWO(Score(2)),
    THREE(Score(3)),
    FOUR(Score(4)),
    FIVE(Score(5)),
    SIX(Score(6)),
    SEVEN(Score(7)),
    EIGHT(Score(8)),
    NINE(Score(9)),
    TEN(Score(10)),
    JACK(Score(10)),
    QUEEN(Score(10)),
    KING(Score(10));

    constructor(score: Score) : this(score, score)

    fun calculateScore(otherDenominations: List<Denomination>): Score {
        val denominations = listOf(this, *otherDenominations.toTypedArray())

        val aceCount = denominations.count { ACE == it }

        val score = denominations
            .map { it.primaryScore }
            .reduce { acc, score -> acc + score }

        return if (aceCount != 0) {
            withAce(aceCount, score)
        } else {
            score
        }
    }

    private tailrec fun withAce(count: Int, score: Score): Score {
        if (count == 0) {
            return score
        }

        if (score <= BLACKJACK_SCORE) {
            return score
        }

        return withAce(count - 1, score - ACE_GAP)
    }

    companion object {
        private val ACE_GAP = ACE.primaryScore - ACE.secondaryScore
        private val BLACKJACK_SCORE = Score(21)
    }
}
