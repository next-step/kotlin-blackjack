package blackjack

class PlayerCards {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card?): Boolean {
        return if (card != null) {
            this._cards.add(card)
        } else {
            false
        }
    }

    fun calculateCardsMaxSum(): Int {
        val allCasesOfSum = getAllCasesOfSum()
        return allCasesOfSum.filter { it <= GAME_LIMIT_NUMBER }
            .max()
    }

    private fun getAllCasesOfSum(): List<Int> {
        val sumOfBasicCards = cards.filter { !it.isAce() }.sumOf { it.number.number[0] }
        val aceCards = cards.filter { it.isAce() }

        return if (aceCards.isEmpty()) {
            listOf(sumOfBasicCards)
        } else {
            aceCards.flatMap { card -> card.number.number.map { it + sumOfBasicCards } }
        }
    }

    companion object {
        const val GAME_LIMIT_NUMBER = 21
    }
}
