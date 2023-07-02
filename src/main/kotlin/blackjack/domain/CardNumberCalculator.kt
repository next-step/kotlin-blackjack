package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

class CardNumberCalculator() {

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

        if (sum + CardNumber.ACE_MAXINUM <= RuleChecker.CONDITION_TO_WIN_BLACK_JACK) {
            return CardNumber.ACE_MAXINUM
        }
        return CardNumber.ACE_MINIMUM
    }
}
