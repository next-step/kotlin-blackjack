package blackjack.service

import blackjack.domain.Card
import blackjack.domain.CardNumberValue
import blackjack.domain.PlayerCards
import kotlin.math.abs

class ResultCalculator {
    fun getCardsResultPoint(cards: List<Card>): Int {
        if (hasAnyAceCard(cards)) {
            return getResultForHavingAnyAceCard(cards)
        }

        return getSumOfMinimumCardValues(cards)
    }

    fun hasAnyAceCard(cards: List<Card>): Boolean {
        return cards.any { card -> card.hasAce() }
    }

    fun getSumOfMinimumCardValues(cards: List<Card>) = sumOfCardValues(cards)

    fun getSumOfMaximumCardValues(cards: List<Card>): Int {
        val firstAceCard = cards.firstOrNull { it.hasAce() }
        val restCards = cards.filter { it != firstAceCard }

        if (firstAceCard == null) {
            return sumOfCardValues(restCards)
        }

        return CardNumberValue.getValue(firstAceCard.number.rank, true).value + sumOfCardValues(restCards)
    }

    private fun sumOfCardValues(targetCards: List<Card>) = targetCards
        .sumOf { CardNumberValue.getValue(it.number.rank).value }

    fun getResultForHavingAnyAceCard(cards: List<Card>): Int {
        val sumOfMinimumCardValues = getSumOfMinimumCardValues(cards)
        val sumOfMaximumCardValues = getSumOfMaximumCardValues(cards)

        if (!exceededMaximumCardValues(sumOfMaximumCardValues) &&
            isMaximumCardValuesCloserToBlackJack(sumOfMinimumCardValues, sumOfMaximumCardValues)
        ) {
            return sumOfMaximumCardValues
        }

        return sumOfMinimumCardValues
    }

    fun exceededMaximumCardValues(sum: Int) = sum > PlayerCards.MAXIMUM_SUM_OF_CARD_NUMBERS

    fun isMaximumCardValuesCloserToBlackJack(
        sumOfMinimumCardValues: Int,
        sumOfMaximumCardValues: Int
    ) =
        abs(PlayerCards.MAXIMUM_SUM_OF_CARD_NUMBERS - sumOfMinimumCardValues) > abs(PlayerCards.MAXIMUM_SUM_OF_CARD_NUMBERS - sumOfMaximumCardValues)
}
