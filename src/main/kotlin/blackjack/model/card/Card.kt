package blackjack.model.card

class Card(
    private val cardType: CardType,
    private val cardNumber: CardNumber
) {

    fun getScore() = cardNumber.score

    fun isAce() = cardNumber.isAce()

    override fun toString(): String {
        return "${cardNumber.score}${cardType.typeName}"
    }
}
