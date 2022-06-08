package blackjack

data class Card(val cardNumber: CardNumber, private val cardShape: CardShape) {
    override fun toString(): String {
        return "${cardNumber.cardName}${cardShape.shapeName}"
    }
}
