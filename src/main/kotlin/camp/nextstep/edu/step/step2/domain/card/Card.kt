package camp.nextstep.edu.step.step2.domain.card

import camp.nextstep.edu.step.step2.domain.card.type.CardNumbers
import camp.nextstep.edu.step.step2.domain.card.type.CardType

class Card(
    val cardNumber: CardNumbers,
    val cardType: CardType
) {
    fun getCardNumber(): Int {
        return cardNumber.number
    }

    fun getCardType(): String {
        return cardType.type
    }

    fun isAce(): Boolean {
        return cardNumber.isAce()
    }

}
