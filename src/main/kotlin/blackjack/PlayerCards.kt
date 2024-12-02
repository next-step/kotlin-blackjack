package blackjack

class PlayerCards() {
    private val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card?) {
        if (card != null) {
            this.cards.add(card)
        }
    }

    fun getCards(): List<Card> {
        return cards.toList()
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
        private const val GAME_LIMIT_NUMBER = 21
    }
}
