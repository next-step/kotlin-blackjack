package blackjack.domain

import blackjack.domain.CardPoint.Companion.MAXIMUM_SUM_OF_CARD_POINTS

class ResultCalculator {
    fun getCardsResultPoint(playerCards: ParticipantCards): Int {
        val defaultCardsResultPoint = playerCards.getSumOfCardsPoint()
        val cardsResultPointWithAce = defaultCardsResultPoint + ACE_EXTRA_POINT

        if (playerCards.hasAceCard() && !isBustedCardsResultPoint(cardsResultPointWithAce)) {
            return cardsResultPointWithAce
        }

        return defaultCardsResultPoint
    }

    fun isBustedCardsResultPoint(sum: Int): Boolean = sum > MAXIMUM_SUM_OF_CARD_POINTS

    companion object {
        private const val ACE_EXTRA_POINT = 10
    }
}
