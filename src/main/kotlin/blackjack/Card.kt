package blackjack

import blackjack.card.CardNumber
import blackjack.card.CardType

data class Card(
    private val cardType: CardType,
    private val cardNumber: CardNumber
) {
    override fun toString(): String {
        return "${cardNumber.score}${cardType.typeName}"
    }

    fun getScore() = cardNumber.score

    companion object {
        fun newInstance(cardType: CardType, cardNumber: CardNumber) = Card(cardType, cardNumber)
    }
}
