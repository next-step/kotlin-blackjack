package blackjack.card

data class Cards(
    private val _cards: MutableList<Card> = mutableListOf(),
) {
    val cards: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun bestScore(): Int {
        return possibleScoreSum().max()
    }

    // TODO: Player에게 묻도록 변경
    fun isBust(): Boolean {
        return possibleScoreSum().all { it > SCORE_LIMIT }
    }

    fun getOpenCard(): String {
        return _cards.first().toString()
    }

    private fun possibleScoreSum(): List<Int> {
        val sumOf = _cards.sumOf { it.number.score }
        if (_cards.any { it.isAce() }) {
            return listOf(sumOf, sumOf + ACE_POINT)
        }
        return listOf(sumOf)
    }

    override fun toString(): String {
        return _cards.joinToString(", ")
    }

    companion object {
        private const val SCORE_LIMIT = 21
        private const val ACE_POINT = 10
        private const val ZERO = 0

        fun createCardPack(): List<Card> {
            return CardSuit.entries.flatMap { suit -> CardNumber.entries.map { Card(it, suit) } }
        }

        fun create(newCards: List<Card>): Cards {
            return Cards(newCards.toMutableList())
        }
    }
}
