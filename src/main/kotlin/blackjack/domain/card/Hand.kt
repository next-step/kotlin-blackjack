package blackjack.domain.card

class Hand(private val _cards: MutableList<Card> = mutableListOf()) {
    val cards: List<Card>
        get() = _cards.toList()

    val score: Int
        get() = calculateScore()

    fun add(card: Card) {
        _cards.add(card)
    }

    private fun calculateScore(): Int {
        val totalScore = cards.sumOf { it.score }
        var aceCount = cards.count { it.isAce }

        var adjustedScore = totalScore
        while (adjustedScore > BLACKJACK_SCORE_LIMIT && aceCount > 0) {
            adjustedScore -= ACE_SCORE_DIFFERENCE
            aceCount--
        }
        return adjustedScore
    }

    companion object {
        private const val BLACKJACK_SCORE_LIMIT = 21
        private const val ACE_SCORE_DIFFERENCE = 10
    }
}
