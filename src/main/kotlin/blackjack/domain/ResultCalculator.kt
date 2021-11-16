package blackjack.domain

import blackjack.domain.CardNumberValue.Companion.ACE_EXTRA_VALUE
import blackjack.domain.ParticipantCards.Companion.MAXIMUM_SUM_OF_CARD_NUMBERS

class ResultCalculator {
    fun getCardsResultPoint(playerCards: ParticipantCards): Int {
        val defaultCardsResultPoint = getDefaultCardsResultPoint(playerCards)
        val cardsResultPointWithAce = defaultCardsResultPoint + ACE_EXTRA_VALUE

        if (hasAnyAceCard(playerCards) && !isBustedCardsResultPoint(cardsResultPointWithAce)) {
            return cardsResultPointWithAce
        }

        return defaultCardsResultPoint
    }

    fun getDefaultCardsResultPoint(playerCards: ParticipantCards): Int = playerCards
        .cards
        .sumOf { CardNumberValue.getValue(it.number.rank).value }

    fun hasAnyAceCard(participantCards: ParticipantCards): Boolean = participantCards
        .cards
        .any { card -> card.hasAce() }

    fun isBustedCardsResultPoint(sum: Int): Boolean = sum > MAXIMUM_SUM_OF_CARD_NUMBERS
}
