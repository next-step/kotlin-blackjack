package blackjack.domain.card

class Hands(val cards: MutableList<Card>) {
    fun draw(card: Card): Boolean {
        return cards.add(card)
    }

    fun calculateScore(): Int {
        val score = cards.map { it.symbol.score }.sum()
        return applyAceCondition(score)
    }

    private fun applyAceCondition(score: Int): Int {
        if (hasAce() && score <= ACE_INCREASE_CONDITION) {
            return score + ACE_INCREASE_SCORE
        }
        return score
    }

    private fun hasAce() = cards.any { it.symbol == CardSymbol.ACE }

    companion object {
        private const val ACE_INCREASE_CONDITION = 11
        private const val ACE_INCREASE_SCORE = 10
    }
}
