package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

class CardNumberCalculator(private val gamerType: GamerType) {

    fun calculateSumOfCardNumbers(cards: List<Card>): Int {
        var sum = 0
        cards.forEach {
            sum += calculateCardNumber(it.number, sum)
        }
        return sum
    }

    fun calculateCardNumber(cardNumber: CardNumber, sum: Int): Int {
        if (CardNumber.isAce(cardNumber)) {
            return proceedAceNumber(sum)
        }
        return cardNumber.value
    }

    private fun proceedAceNumber(sum: Int): Int {
        val conditionToProceedAceNumber = proceedConditionAceNumber()

        if (sum + CardNumber.ACE_MAXINUM <= conditionToProceedAceNumber) {
            return CardNumber.ACE_MAXINUM
        }
        return CardNumber.ACE_MINIMUM
    }

    private fun proceedConditionAceNumber(): Int {
        var conditionToProceedAceNumber = RuleChecker.CONDITION_TO_WIN_BLACK_JACK
        if (gamerType == GamerType.DEALER) conditionToProceedAceNumber = RuleChecker.CONDITION_TO_DEALER_DRAW_CARD
        return conditionToProceedAceNumber
    }
}
