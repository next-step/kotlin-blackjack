package baclkjack.domain.card

class Cards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    val isWinningNumber: Boolean
        get() = score() == WIN_NUMBER

    val isBlackJack: Boolean
        get() = isWinningNumber && _cards.size == FIRST_DRAW

    val isBurst: Boolean
        get() = score() > WIN_NUMBER

    fun score(): Int {
        val score = _cards.map { it.number }.sumOf { it.value }
        if (isSoft() && isNotBurst(score + ACE_VALUE)) {
            return score + ACE_VALUE
        }
        return score
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    private fun isSoft(): Boolean = _cards.any { it.number.isAce() }

    private fun isNotBurst(score: Int) = score <= WIN_NUMBER

    companion object {
        const val ACE_VALUE = 10
        const val WIN_NUMBER = 21
        const val FIRST_DRAW = 2
    }
}
