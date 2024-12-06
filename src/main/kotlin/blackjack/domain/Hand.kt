package blackjack.domain

class Hand {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return calculateBestTotal() == ZERO
    }

    fun isBlackjack(): Boolean {
        return cards.size == 2 && calculateBestTotal() == BLACKJACK_NUMBER
    }

    fun calculateBestTotal(): Int {
        val possibleSums = calculateAllPossibleSums()
        return possibleSums.filter { it <= BLACKJACK_NUMBER }.maxOrNull() ?: ZERO
    }

    private fun calculateAllPossibleSums(): List<Int> {
        val sumOf = cards.sumOf { it.rank.value }
        if (cards.any { it.isAce() }) {
            return listOf(sumOf, sumOf + 10)
        }
        return listOf(sumOf)
    }

    fun getAllCards(): List<Card> {
        return cards.toList()
    }

    fun getSpecificRangeCards(
        start: Int,
        end: Int,
    ): List<Card> {
        return cards.subList(start, end)
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val ZERO = 0

        fun createInitial(cards: List<Card>): Hand {
            return Hand().apply {
                cards.forEach { card ->
                    addCard(card)
                }
            }
        }
    }
}
