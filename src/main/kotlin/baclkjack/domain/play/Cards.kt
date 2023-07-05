package baclkjack.domain.play

import baclkjack.domain.card.Card

class Cards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()
    fun add(card: Card) {
        _cards.add(card)
    }

    fun score(): Int {
        val score = _cards.map { it.number }.sumOf { it.value }
        if (isSoft() && isNotBurst(score + ACE_VALUE)) {
            return score + ACE_VALUE
        }
        return score
    }

    private fun isSoft(): Boolean = _cards.any { it.number.isAce() }

    private fun isNotBurst(score: Int) = score <= WIN_NUMBER

    fun isBurst(): Boolean = score() > WIN_NUMBER

    fun isBlackJack(): Boolean = score() == WIN_NUMBER

    companion object {
        const val ACE_VALUE = 10
        const val WIN_NUMBER = 21
    }
}
