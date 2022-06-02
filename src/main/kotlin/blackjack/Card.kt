package blackjack

class Card(val cardNumber: CardNumber, val cardShape: CardShape) {
    override fun toString(): String {
        return "${cardNumber.cardName}${cardShape.shapeName}"
    }
}
