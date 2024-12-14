package blackjack.domain

class PlayerCards {
    val cards: Cards = Cards()

    fun addCard(card: Card): Boolean {
        return this.cards.add(card)
    }

    fun calculateCardsMaxSum(): Int {
        val allCasesOfSum = getAllCasesOfSum()
        return allCasesOfSum.filter { it <= GAME_LIMIT_NUMBER }
            .maxOrNull() ?: ZERO
    }

    private fun getAllCasesOfSum(): List<Int> {
        val sumOfBasicCards = cards.group.filter { !it.isAce() }.sumOf { it.number.number[0] }
        val aceCards = cards.group.filter { it.isAce() }

        return if (aceCards.isEmpty()) {
            listOf(sumOfBasicCards)
        } else {
            aceCards.flatMap { card -> card.number.number.map { it + sumOfBasicCards } }
        }
    }

    companion object {
        const val GAME_LIMIT_NUMBER = 21
        const val ZERO = 0
    }
}
