package blackjack.domain.card

class Hands(private val _cards: MutableList<Card>) {
    val cards
        get() = _cards.toList()

    fun draw(card: Card): Boolean {
        return _cards.add(card)
    }

    fun calculateScore(): Int {
        val score = _cards.map { it.symbol.score }.sum()
        return applyAceCondition(score)
    }

    private fun applyAceCondition(score: Int): Int {
        if (hasAce() && score <= ACE_INCREASE_CONDITION) {
            return score + ACE_INCREASE_SCORE
        }
        return score
    }

    private fun hasAce() = _cards.any { it.symbol == CardSymbol.ACE }

    companion object {
        private const val ACE_INCREASE_CONDITION = 11
        private const val ACE_INCREASE_SCORE = 10
    }
}
