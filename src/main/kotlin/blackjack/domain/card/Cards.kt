package blackjack.domain.card

data class Cards(
    val cards: MutableList<Card> = mutableListOf(),
) {
    override fun toString(): String {
        return cards.joinToString(DELIMITER)
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun pickFirst(): Card {
        return cards.removeFirst()
    }

    fun countCards(): Int {
        return cards.size
    }

    fun calculate(): Int {
        val aceCnt = cards.count { it.isAce() }
        var score = cards.fold(0) { acc, card -> card.calculate(acc) }
        repeat(aceCnt) {
            score = calculateAceCard(score)
        }
        return score
    }

    private fun calculateAceCard(score: Int): Int {
        val calculatedScore = score + ACE_EXTRA_SCORE
        if (calculatedScore > MAXIMUM_SCORE) {
            return score
        }
        return calculatedScore
    }

    companion object {
        private const val DELIMITER = ", "
        private const val MAXIMUM_SCORE = 21
        private const val ACE_EXTRA_SCORE = 10
    }
}
