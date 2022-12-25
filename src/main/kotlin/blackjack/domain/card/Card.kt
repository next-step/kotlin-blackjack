package blackjack.domain.card

class Card(
    private val cardNumber: CardNumber,
    private val cardShape: CardShape
) {
    override fun toString(): String {
        return cardNumber.toString() + cardShape.toString()
    }

    fun mainScore() = cardNumber.mainScore

    fun secondaryScore() = cardNumber.secondaryScore
}
