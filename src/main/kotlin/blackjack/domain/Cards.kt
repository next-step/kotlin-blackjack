package blackjack.domain

class Cards(
    private val cards: MutableList<Card> = mutableListOf(),
) : List<Card> by cards {

    fun add(card: Card): Boolean {
        return cards.add(card)
    }

    fun calculateScore(): Int {
        val score = cards.sumOf { it.rank.score }

        if (cards.any { it.rank == Rank.ACE } && score + ACE_HIGH_SCORE <= MAX_SCORE) {
            return score + ACE_HIGH_SCORE
        }

        return score
    }

    fun isOverMaxScore(): Boolean {
        return calculateScore() >= MAX_SCORE
    }

    companion object {
        private const val MAX_SCORE = 21
        private const val ACE_HIGH_SCORE = 10
    }
}
