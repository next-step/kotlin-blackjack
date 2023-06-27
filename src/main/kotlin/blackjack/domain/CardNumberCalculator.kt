package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

class CardNumberCalculator(private val proceedAceStrategy: ProceedAceStrategy) {


    fun calculateSumOfCardNumbers(cards: List<Card>): Int {
        var sum = 0
        cards.forEach {
            sum += calculateCardNumber(it.number, sum)
        }
        return sum
    }

    fun calculateCardNumber(cardNumber: CardNumber, sum: Int): Int {
        if (CardNumber.isAce(cardNumber)) {
            return proceedAceStrategy.proceedAceNumber(sum)
        }
        return cardNumber.value
    }

}
