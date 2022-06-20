package blackjack.domain.card

import blackjack.domain.card.Card.AceCard

data class ReceivedCards(private val receivedCards: MutableSet<Card>) {

    fun sumOfCardsExceptAce(): Int {
        return receivedCards
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
        receivedCards.add(card)
    }

    fun count(): Int {
        return receivedCards.size
    }

    fun getCardDescription(): String {
        return receivedCards.joinToString(", ") { extractCardDescription(it) }
    }

    private fun countOfSoftHand(): Int {
        return receivedCards.count { it is AceCard }
    }

    private fun extractCardDescription(card: Card): String {
        return when (card) {
            is AceCard -> "A${card.cardSuit.description}"
            is Card.JackCard -> "J${card.cardSuit.description}"
            is Card.QueenCard -> "Q${card.cardSuit.description}"
            is Card.KingCard -> "K${card.cardSuit.description}"
            is Card.BasicCard -> "${card.number}${card.cardSuit.description}"
        }
    }

    companion object {
        private val ACE_COUNT_ONE_CASE = listOf(1, 11)
        private val ACE_COUNT_TWO_CASE = listOf(2, 12)
        private val ACE_COUNT_THREE_CASE = listOf(3, 13)
        private val ACE_COUNT_FOUR_CASE = listOf(4, 14)
    }
}
