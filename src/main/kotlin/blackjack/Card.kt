package blackjack

class Card(val cardNumber: CardNumber, private val cardShape: CardShape) {
    override fun toString(): String {
        return "${cardNumber.cardName}${cardShape.shapeName}"
    }

    override fun hashCode(): Int {
        return cardNumber.name.hashCode() + cardShape.name.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Card) return false
        return cardNumber == other.cardNumber && cardShape == other.cardShape
    }
}
