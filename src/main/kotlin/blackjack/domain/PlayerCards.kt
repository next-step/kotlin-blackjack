package blackjack.domain

class PlayerCards {
    val cards: Cards = Cards()

    fun addCard(card: Card): Boolean {
        return this.cards.add(card)
    }

    fun calculateCardsMaxSum(): Int {
        val allCasesOfSum = getAllCasesOfSum()
        return allCasesOfSum.filter { it <= Cards.GAME_LIMIT_NUMBER }
            .maxOrNull() ?: ZERO
    }

    private fun getAllCasesOfSum(): List<Int> {
        val sumOfBasicCards = cards.getSumOfBasicCards()
        return if (!cards.hasAceCards()) {
            listOf(sumOfBasicCards)
        } else {
            cards.getAceCards().flatMap { card -> card.number.number.map { it + sumOfBasicCards } }
        }
    }

    companion object {
        const val ZERO = 0
    }
}
