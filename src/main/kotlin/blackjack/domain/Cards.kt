package blackjack.domain

class Cards(
    private val cards: MutableList<Card> = mutableListOf()
) : List<Card> by cards {

    fun add(card: Card) {
        cards.add(card)
    }

    fun calculateScore(): Int {
        val score = cards.sumOf { it.rank.score }

        if (cards.any { it.rank == Rank.ACE } && score + 10 <= MAX_SCORE) {
            return score + 10
        }

        return score
    }

    fun isOverMaxScore(): Boolean = calculateScore() >= MAX_SCORE

    companion object {
        const val MAX_SCORE = 21
    }
}
