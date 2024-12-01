package blackjack.domain

class Hand {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card) {
        cards.add(card)
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

    fun getCards(): List<Card> {
        return cards.toList()
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
