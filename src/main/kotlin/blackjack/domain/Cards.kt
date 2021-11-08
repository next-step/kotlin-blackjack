package blackjack.domain

import kotlin.math.abs

class Cards {
    val cards: MutableList<Card> = mutableListOf()

    private fun hasAnyAceCard(): Boolean {
        return cards.any { card -> card.isAceCard() }
    }

    fun add(card: Card) {
        cards.add(card)
    }

    override fun toString(): String {
        return cards.joinToString { it.toString() }
    }

    fun canReceiveAdditionalCard(): Boolean {
        return getSumOfMinimumCardValues() < MAXIMUM_SUM_OF_CARD_NUMBERS
    }

    private fun getSumOfMinimumCardValues() = sumOfCardValues(cards)

    private fun getSumOfMaximumCardValues(): Int {
        val firstAceCard = cards.firstOrNull { it.isAceCard() }
        val restCards = cards.filter { it != firstAceCard }

        if (firstAceCard == null) {
            return sumOfCardValues(restCards)
        }

        return CardNumberValue.getValue(firstAceCard.number.text, true).value + sumOfCardValues(restCards)
    }

    private fun sumOfCardValues(targetCards: List<Card>) =
        targetCards.sumOf { CardNumberValue.getValue(it.number.text).value }

    fun getResult(): Int {
        if (hasAnyAceCard()) {
            return getResultForHavingAnyAceCard()
        }

        return getSumOfMinimumCardValues()
    }

    private fun getResultForHavingAnyAceCard(): Int {
        val sumOfMinimumCardValues = getSumOfMinimumCardValues()
        val sumOfMaximumCardValues = getSumOfMaximumCardValues()

        if (!exceededMaximumCardValues(sumOfMaximumCardValues) &&
            isMaximumCardValuesMoreProperResult(sumOfMinimumCardValues, sumOfMaximumCardValues)
        ) {
            return sumOfMaximumCardValues
        }

        return sumOfMinimumCardValues
    }

    private fun exceededMaximumCardValues(sum: Int) = sum > MAXIMUM_SUM_OF_CARD_NUMBERS

    private fun isMaximumCardValuesMoreProperResult(
        sumOfMinimumCardValues: Int,
        sumOfMaximumCardValues: Int
    ) =
        abs(MAXIMUM_SUM_OF_CARD_NUMBERS - sumOfMinimumCardValues) > abs(MAXIMUM_SUM_OF_CARD_NUMBERS - sumOfMaximumCardValues)

    companion object {
        private const val MAXIMUM_SUM_OF_CARD_NUMBERS = 21
    }
}
