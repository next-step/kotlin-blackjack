package blackjack.domain.card

import blackjack.domain.card.Card.AceCard
import blackjack.domain.game.Score

data class ReceivedCards(private val _receivedCards: MutableSet<Card>) {

    val receivedCards: Set<Card>
        get() = _receivedCards

    fun addCard(card: Card) {
        _receivedCards.add(card)
    }

    fun count(): Int {
        return _receivedCards.size
    }

    private fun sumOfCardsExceptAce(): Int {
        return _receivedCards
            .filter { it !is AceCard }
            .sumOf { it.number }
    }

    fun calculateScore(): Score {
        val sumOfExceptAce = sumOfCardsExceptAce()
        val totalSumsByAceCount = calculationPoliciesByAceCount()
            .map { sumOfAce -> sumOfAce + sumOfExceptAce }

        if (totalSumsByAceCount.contains(BLACKJACK_SCORE)) {
            return Score(BLACKJACK_SCORE)
        }

        return Score(totalSumsByAceCount.minOrNull() ?: 0)
    }

    private fun calculationPoliciesByAceCount(): List<Int> {
        return when (countOfSoftHand(_receivedCards)) {
            1 -> ACE_COUNT_ONE_CASE
            2 -> ACE_COUNT_TWO_CASE
            3 -> ACE_COUNT_THREE_CASE
            4 -> ACE_COUNT_FOUR_CASE
            else -> listOf(0)
        }
    }

    private fun countOfSoftHand(receivedCards: Set<Card>): Int {
        return receivedCards.count { it is Card.AceCard }
    }

    companion object {
        val ACE_COUNT_ONE_CASE = listOf(1, 11)
        val ACE_COUNT_TWO_CASE = listOf(2, 12)
        val ACE_COUNT_THREE_CASE = listOf(3, 13)
        val ACE_COUNT_FOUR_CASE = listOf(4, 14)
        const val BLACKJACK_SCORE = 21
    }
}
