package blackjack.domain

class Hand {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card): Boolean {
        if (canAddWithoutExceeding()) {
            cards.add(card)
            return true
        }
        return false
    }

    private fun canAddWithoutExceeding(): Boolean {
        val allPossibleSums = calculateAllPossibleSums()
        return allPossibleSums.any { it < BLACKJACK_NUMBER }
    }

    fun calculateBestTotal(): Int {
        val possibleSums = calculateAllPossibleSums()
        return possibleSums.filter { it <= BLACKJACK_NUMBER }.maxOrNull() ?: ZERO
    }

    private fun calculateAllPossibleSums(): List<Int> {
        return cards.fold(listOf(ZERO)) { sums, card ->
            card.possibleSums(sums)
        }
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val ZERO = 0
    }
}
