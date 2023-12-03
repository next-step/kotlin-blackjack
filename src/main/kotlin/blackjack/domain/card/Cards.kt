package blackjack.domain.card

import blackjack.domain.Score

class Cards {

    private val cards = mutableListOf<Card>()
    val values: List<Card>
        get() = cards

    fun add(card: Card) {
        cards.add(card)
    }

    fun calculateScore(): Score {
        val currentScore = calculateCardScore()
        val aceCount = calculateAceCount()

        return (0 until aceCount).fold(currentScore) { score, _ ->
            addSpecialScoreIfPossible(score)
        }
    }

    fun isBust(): Boolean {
        return calculateScore() > BLACKJACK_SCORE
    }

    fun isBlackjack(): Boolean {
        return calculateScore() == BLACKJACK_SCORE
    }

    fun first(): Cards {
        val firstCard = cards.first()
        return Cards().apply {
            add(firstCard)
        }
    }

    private fun calculateCardScore(): Score {
        val score = cards.sumOf { it.number.score.value }
        return Score(score)
    }

    private fun calculateAceCount(): Int {
        return cards.count { it.number.isAce() }
    }

    private fun addSpecialScoreIfPossible(score: Score): Score {
        val totalScore = score + ACE_SPECIAL_SCORE
        if (totalScore <= BLACKJACK_SCORE) {
            return totalScore
        }
        return score
    }

    companion object {
        private val BLACKJACK_SCORE = Score(21)
        private val ACE_SPECIAL_SCORE = Score(10)
    }
}
