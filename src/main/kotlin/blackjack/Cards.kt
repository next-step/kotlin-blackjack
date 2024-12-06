package blackjack

data class Cards(private val _cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        _cards.add(card)
    }

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun bestScore(): Int {
        return possibleScoreSum().filter { it <= BLACKJACK }.max()
    }

    fun isBust(): Boolean {
        return possibleScoreSum().any { it >= BLACKJACK }
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
        private const val BLACKJACK = 21
        private const val ACE_POINT = 10

        fun createCardPack(): List<Card> {
            return CardSuit.entries.flatMap { suit -> CardNumber.entries.map { Card(it, suit) } }
        }
    }
}
