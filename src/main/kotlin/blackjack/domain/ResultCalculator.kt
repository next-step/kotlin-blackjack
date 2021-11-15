package blackjack.domain

import kotlin.math.abs

class ResultCalculator {
    fun getCardsResultPoint(playerCards: ParticipantCards): Int {
        if (hasAnyAceCard(playerCards)) {
            return getResultForHavingAnyAceCard(playerCards)
        }

        return getSumOfMinimumCardValues(playerCards)
    }

    fun hasAnyAceCard(playerCards: ParticipantCards): Boolean {
        return playerCards.cards.any { card -> card.hasAce() }
    }

    fun getSumOfMinimumCardValues(playerCards: ParticipantCards) = sumOfCardValues(playerCards.cards)

    fun getSumOfMaximumCardValues(playerCards: ParticipantCards): Int {
        val firstAceCard = playerCards.cards.firstOrNull { it.hasAce() }
        val restCards = playerCards.cards.filter { it != firstAceCard }

        if (firstAceCard == null) {
            return sumOfCardValues(restCards)
        }

        return CardNumberValue.getValue(firstAceCard.number.rank, true).value + sumOfCardValues(restCards)
    }

    private fun sumOfCardValues(targetCards: List<Card>) = targetCards
        .sumOf { CardNumberValue.getValue(it.number.rank).value }

    fun getResultForHavingAnyAceCard(playerCards: ParticipantCards): Int {
        val sumOfMinimumCardValues = getSumOfMinimumCardValues(playerCards)
        val sumOfMaximumCardValues = getSumOfMaximumCardValues(playerCards)

        if (!exceededMaximumCardValues(sumOfMaximumCardValues) &&
            isMaximumCardValuesCloserToBlackJack(sumOfMinimumCardValues, sumOfMaximumCardValues)) {
            return sumOfMaximumCardValues
        }

        return sumOfMinimumCardValues
    }

    fun exceededMaximumCardValues(sum: Int) = sum > PlayerCards.MAXIMUM_SUM_OF_CARD_NUMBERS

    fun isMaximumCardValuesCloserToBlackJack(
        sumOfMinimumCardValues: Int,
        sumOfMaximumCardValues: Int
    ) = abs(PlayerCards.MAXIMUM_SUM_OF_CARD_NUMBERS - sumOfMinimumCardValues) >
        abs(PlayerCards.MAXIMUM_SUM_OF_CARD_NUMBERS - sumOfMaximumCardValues)
}
