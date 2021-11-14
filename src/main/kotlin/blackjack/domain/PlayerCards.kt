package blackjack.domain

import kotlin.math.abs

class PlayerCards {
    val cards: MutableList<Card> = mutableListOf()

    override fun toString(): String {
        return cards.joinToString { it.toString() }
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun canReceiveAdditionalCard(): Boolean = getSumOfMinimumCardValues() < MAXIMUM_SUM_OF_CARD_NUMBERS

    fun getCardsResultPoint(): Int {
        if (hasAnyAceCard()) {
            return getResultForHavingAnyAceCard()
        }

        return getSumOfMinimumCardValues()
    }

    fun hasAnyAceCard(): Boolean {
        return cards.any { card -> card.hasAce() }
    }

    fun getSumOfMinimumCardValues() = sumOfCardValues(cards)

    fun getSumOfMaximumCardValues(): Int {
        val firstAceCard = cards.firstOrNull { it.hasAce() }
        val restCards = cards.filter { it != firstAceCard }

        if (firstAceCard == null) {
            return sumOfCardValues(restCards)
        }

        return CardNumberValue.getValue(firstAceCard.number.rank, true).value + sumOfCardValues(restCards)
    }

    private fun sumOfCardValues(targetCards: List<Card>) = targetCards
        .sumOf { CardNumberValue.getValue(it.number.rank).value }

    fun getResultForHavingAnyAceCard(): Int {
        val sumOfMinimumCardValues = getSumOfMinimumCardValues()
        val sumOfMaximumCardValues = getSumOfMaximumCardValues()

        if (!exceededMaximumCardValues(sumOfMaximumCardValues) &&
            isMaximumCardValuesCloserToBlackJack(sumOfMinimumCardValues, sumOfMaximumCardValues)
        ) {
            return sumOfMaximumCardValues
        }

        return sumOfMinimumCardValues
    }

    fun exceededMaximumCardValues(sum: Int) = sum > MAXIMUM_SUM_OF_CARD_NUMBERS

    fun isMaximumCardValuesCloserToBlackJack(
        sumOfMinimumCardValues: Int,
        sumOfMaximumCardValues: Int
    ) =
        abs(MAXIMUM_SUM_OF_CARD_NUMBERS - sumOfMinimumCardValues) > abs(MAXIMUM_SUM_OF_CARD_NUMBERS - sumOfMaximumCardValues)

    companion object {
        private const val MAXIMUM_SUM_OF_CARD_NUMBERS = 21
    }
}
