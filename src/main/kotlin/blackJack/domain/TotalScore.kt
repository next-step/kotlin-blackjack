package blackJack.domain

object TotalScore {
    fun getScore(cards: List<Int>): Int {
        if (isAce(cards)) {
            return hasAce(cards.sum())
        }
        return cards.sum()
    }

    private fun isAce(cards: List<Int>): Boolean = cards.contains(1)

    private fun hasAce(totalScore: Int): Int {
        if (totalScore <= 11) {
            return totalScore + 10
        }
        return totalScore
    }
}
