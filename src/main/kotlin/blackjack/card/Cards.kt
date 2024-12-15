package blackjack.card

data class Cards(
    private val _cards: MutableList<Card> = mutableListOf(),
) {
    val cards: List<Card>
        get() = _cards.toList()

    fun add(newCard: Card) {
        _cards.add(newCard)
    }

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun bestScore(): Int {
        return possibleScoreSum()
            .filter { it <= MAX_SCORE }
            .maxOrNull() ?: possibleScoreSum().min()
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
        private const val ACE_POINT = 10
        private const val MAX_SCORE = 21

        fun createShuffledCardPack(): List<Card> {
            return CardSuit.entries.flatMap { suit -> CardNumber.entries.map { Card(it, suit) } }.shuffled()
        }
    }
}
