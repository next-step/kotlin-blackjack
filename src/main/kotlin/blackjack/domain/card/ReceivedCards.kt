package blackjack.domain.card

import blackjack.domain.card.Card.AceCard

data class ReceivedCards(private val _receivedCards: MutableSet<Card>) {

    val receivedCards: Set<Card>
        get() = _receivedCards

    fun sumOfCardsExceptAce(): Int {
        return _receivedCards
            .filter { it !is AceCard }
            .sumOf { it.number }
    }

    fun calculationPoliciesByAceCount(): List<Int> {
        return when (countOfSoftHand()) {
            1 -> ACE_COUNT_ONE_CASE
            2 -> ACE_COUNT_TWO_CASE
            3 -> ACE_COUNT_THREE_CASE
            4 -> ACE_COUNT_FOUR_CASE
            else -> listOf(0)
        }
    }

    fun addCard(card: Card) {
        _receivedCards.add(card)
    }

    fun count(): Int {
        return _receivedCards.size
    }

    private fun countOfSoftHand(): Int {
        return _receivedCards.count { it is AceCard }
    }

    companion object {
        private val ACE_COUNT_ONE_CASE = listOf(1, 11)
        private val ACE_COUNT_TWO_CASE = listOf(2, 12)
        private val ACE_COUNT_THREE_CASE = listOf(3, 13)
        private val ACE_COUNT_FOUR_CASE = listOf(4, 14)
    }
}
