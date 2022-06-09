package blackjack.model

class Card(val cardNumber: CardNumber, val suit: Suit) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (cardNumber != other.cardNumber) return false
        if (suit != other.suit) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cardNumber.hashCode()
        result = 31 * result + suit.hashCode()
        return result
    }
}
