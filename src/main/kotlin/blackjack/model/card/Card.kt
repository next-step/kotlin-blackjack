package blackjack.model.card

data class Card(
    private val cardType: CardType,
    private val cardNumber: CardNumber
) {

    fun getScore() = cardNumber.score

    fun isAce() = cardNumber.isAce()

    override fun toString(): String {
        return "${cardNumber.score}${cardType.typeName}"
    }

    companion object {
        fun newInstance(cardType: CardType, cardNumber: CardNumber) =
            Card(cardType, cardNumber)
    }
}
