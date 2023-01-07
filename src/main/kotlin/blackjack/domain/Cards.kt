package blackjack.domain

import blackjack.common.Policy

data class Cards(
    private val cards: MutableList<Card> = mutableListOf(),
) : List<Card> by cards {
    companion object {
        private const val ZERO = 0
        private const val ACE_EXTRA_SCORE = 10
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun pop(): Card {
        return cards.removeFirst()
    }

    fun getScore(): Int {
        val aceCnt = cards.count { it.isAce() }
        var score = cards.fold(ZERO) { acc, card -> card.calculate(acc) }
        repeat(aceCnt) { score += calculateAceCard(score) }

        return score
    }

    private fun calculateAceCard(score: Int): Int {
        val calculatedScore = score + ACE_EXTRA_SCORE
        if (calculatedScore > Policy.BUST_SCORE) {
            return ZERO
        }

        return ACE_EXTRA_SCORE
    }
}
