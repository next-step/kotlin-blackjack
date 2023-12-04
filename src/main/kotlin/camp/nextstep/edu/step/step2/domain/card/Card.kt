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

    /**
     * @description : 카드가 에이스인지 아닌지를 확인한다.
     */
    fun isAce(): Boolean {
        return cardNumber.isAce()
    }

}
