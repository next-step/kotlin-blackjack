package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

class CardNumberCalculator {

    fun calculateSumOfCardNumbers(cards: List<Card>): Int {
        var sum = 0
        cards.forEach {
            sum += calculateCardNumber(it.number, sum)
        }
        return sum
    }

    fun calculateCardNumber(cardNumber: CardNumber, sum: Int): Int {
        if (cardNumber == CardNumber.A) {
            return proceedAceNumber(sum)
        }
        return cardNumber.value
    }

    fun proceedAceNumber(sum: Int): Int {
        if (sum + ACE_MAXINUM <= CONDITION_TO_WIN_BLACK_JACK) {
            return ACE_MAXINUM
        }
        return ACE_MINIMUM
    }

    companion object {
        const val CONDITION_TO_WIN_BLACK_JACK = 21
        const val ACE_MAXINUM = 11
        const val ACE_MINIMUM = 1
    }
}
