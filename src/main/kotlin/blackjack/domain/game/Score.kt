package blackjack.domain.game

import blackjack.domain.card.ReceivedCards

object Score {

    fun calculateScore(receivedCards: ReceivedCards): Int {
        val sumOfExceptAce = receivedCards.sumOfCardsExceptAce()
        val totalSumsByAceCount = receivedCards.calculationPoliciesByAceCount()
            .map { sumOfAce -> sumOfAce + sumOfExceptAce }

        if (totalSumsByAceCount.contains(BLACKJACK_SCORE)) {
            return BLACKJACK_SCORE
        }

        return totalSumsByAceCount.minOrNull() ?: 0
    }

    private const val BLACKJACK_SCORE = 21
}
